// Реализуйте структуру телефонной книги с помощью HashMap, учитывая, 
// что 1 человек может иметь несколько телефонов.

// Написать программу, которая выведет список людей с их телефонами
// Отсортировать по колличеству телефонов.

package jv.seminar5;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

public class task {
    public static void main(String[] args) {
        String text = "Евгений,Лавренов,89002242998;" +
                "Евгений,Лавренов,89010000000;" +
                "Евгений,Лавренов,89019855588;" +
                "Снежана,Соловьева,89019999999;" +
                "Снежана,Соловьева,89018888888;" +
                "Наташа,Горбатова,89017777777;" +
                "Миша,Рубан,89016666666;" +
                "Алеша,Синькевич,89015555555;";

        task hw = new task();
        Map<Long, List<String>> phonebook = hw.SpisokTelePhone(text);
        System.out.println("Телефонная книга: " + String.valueOf(phonebook));
        System.out.println("Результат: " + String.valueOf(hw.SortCountTelePhone(phonebook)));

    }

    public Map<Long, List<String>> SpisokTelePhone (String text) {
		List<String> textList = Arrays.asList(text.split(";"));
		Map<Long, List<String>> phonebook = new HashMap<>();

		for (String item : textList) {
			List<String> tmp = Arrays.asList(item.split(","));
			if (tmp.size() == 3) {
				phonebook.putIfAbsent(Long.parseLong(tmp.get(2)), Arrays.asList(tmp.get(0), tmp.get(1)));
			} else {
				throw new IllegalStateException("Неверный формат входных данных");
			}
		}

		return phonebook;
	}


    public Map<Integer, List<String>> SortCountTelePhone (Map<Long, List<String>> phonebook) {
		Map<String, Integer> identicalContacts = new HashMap<>();
		for (List<String> item : phonebook.values()) {
			identicalContacts.put(String.join(" ", item), 1 + identicalContacts.getOrDefault(String.join(" ", item),0));
		}

		Map<Integer, List<String>> result = new TreeMap<>(Collections.reverseOrder());
		for (String key : identicalContacts.keySet()) {
			if (identicalContacts.get(key) > 1) {
				if (result.containsKey(identicalContacts.get(key))) {
					List<String> tmp = result.get(identicalContacts.get(key));
					tmp.add(key);
					result.put(identicalContacts.get(key), tmp);
				} else {
					List<String> tmp = new ArrayList<>(List.of(key));
					result.put(identicalContacts.get(key), tmp);
				}
			}
		}
		return result;
	}
}
