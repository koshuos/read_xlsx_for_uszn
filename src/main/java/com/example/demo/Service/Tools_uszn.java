package com.example.demo.Service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Tools_uszn {

    public ArrayList<String> getPrimList() {
        ArrayList<String> prim_list = new ArrayList<>();
        prim_list.add("<p><b>Вакансія потребує наявність освіти відповідного професійного спрявування</b> - {0}</p>");
        prim_list.add("<p><b>Освітньо-кваліфікаційний рівень</b> - {1}</p>");
        prim_list.add("<p><b>Вакансія потребує наявність додаткових сертифікатів, документів (так/ні)</b> - {2}</p>");
        prim_list.add("<p><b>Примітка</b> - {3}</p>");
        return prim_list;
    }
}
