package org.example;

import org.example.model.City;
import org.example.model.Country;
import org.example.model.Mayor;
import java.util.List;

import static org.example.Db.*;

/**
 * Aidana
 *
 */
public class App {
    public static void main(String[] args) {
       createTablePeople();
        insertTablePeople( 1,"Sadyr Zhaparov", 52);
        insertTablePeople( 2,"Joe  Biden", 48);
        insertTablePeople( 3,"Andreas Manuel", 69);
        insertTablePeople( 4,"Alex Bionu", 53);
        insertTablePeople( 5,"Charles Column", 57);
        insertTablePeople( 6,"Tiffani Monuela", 35);
        List<Mayor> allMayors = getAllMayors();
        System.out.println(allMayors);

        createTableCountry();
        insertTableCountry(1, "Kyrgyzstan", ".kg");
        insertTableCountry(2, "Russia", ".ru");
        insertTableCountry(3, "The USA", ".us");
        insertTableCountry(4, "The UK", ".uk");
        insertTableCountry(5, "Germany", ".de");
        insertTableCountry(6, "France", ".fr");
        List<Country> countries = countryList();
        System.out.println(countries);

        createTableCity();
        insertTableCity(1, "Bishkek");
        insertTableCity(2, "Osh");
        insertTableCity(3, "New York");
        insertTableCity(4, "California");
        insertTableCity(5, "Boston");
        insertTableCity(6, "Hollywood");
        List<City> cities = cityList();
        System.out.println(cities);
        getCityById(1);
    }
}

