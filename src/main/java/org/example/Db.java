package org.example;

import org.example.model.City;
import org.example.model.Country;
import org.example.model.Mayor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private static final String url = "jdbc:postgresql://localhost:5432/jdbc-practice";
    private static final String username = "postgres";
    private static final String password = "123456";

    public static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to db successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public static void createTablePeople() {
        String sql = "CREATE TABLE IF NOT EXISTS people(" +
                " id INT PRIMARY KEY," +
                " name VARCHAR(50) NOT NULL ," +
                " age INT);";
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("CREATE TABLE created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTablePeople(int id, String name, int age) {
        String SQL = " Insert into people( name, age) values (?,?,?)";
        try (Connection connection = Db.connect();
             PreparedStatement prep1 = connection.prepareStatement(SQL)) {
            prep1.setInt(1, id);
            prep1.setString(2, name);
            prep1.setInt(3, age);
            prep1.executeUpdate();
            System.out.println("Successfully added " + name);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "insert table error");
        }
    }
    public static List<Mayor> getAllMayors() {
        String sql = "SELECT * FROM people";
        List<Mayor> people = new ArrayList<>();
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Mayor head = new Mayor();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                head.setId(id);
                head.setName(name);
                head.setAge(age);
                people.add(head);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return people;
    }

    public static void createTableCountry() {
        String sql = "CREATE TABLE IF NOT EXISTS countries(" +
                " id INT PRIMARY KEY," +
                " name VARCHAR(50) NOT NULL UNIQUE ," +
                " domain varchar(20) NOT NULL" +
                " people_id INT REFERENCES people(id));";
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("CREATE TABLE created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTableCountry(int id, String name, String domain) {
        String SQL = " Insert into country (id, name,  domain) values (?,?,?)";
        try (Connection connection = Db.connect();
             PreparedStatement prep = connection.prepareStatement(SQL)) {
            prep.setInt(1, id);
            prep.setString(2, name);
            prep.setString(3, domain);
            prep.executeUpdate(SQL);
            System.out.println("Successfully added " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Country> countryList() {
        String sql1 = "SELECT * FROM country";
        List<Country> countries = new ArrayList<>();
        try (Connection conn1 = Db.connect();
             Statement statement1 = conn1.createStatement();
             ResultSet resultSet1 = statement1.executeQuery(sql1)) {
            while (resultSet1.next()) {
                Country country = new Country();
                int id = resultSet1.getInt("id");
                String name = resultSet1.getString("name");
                String domain = resultSet1.getString("domain");
                country.setId(id);
                country.setName(name);
                country.setDomain(domain);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }

    public static void createTableCity() {
        String sql = "CREATE TABLE IF NOT EXISTS cities(" +
                " id INT PRIMARY KEY," +
                " name VARCHAR(50) NOT NULL ," +
                "people_id INT REFERENCES people(id)" +
                "country_id INT REFERENCES country(id))";
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("CREATE TABLE created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTableCity(int id, String name) {
        String SQL = " Insert into city(id, name) values (?,?)";
        try (Connection connection = Db.connect();
             PreparedStatement prep = connection.prepareStatement(SQL)) {
            prep.setInt(1, id);
            prep.setString(2, name);
            prep.executeUpdate();
            System.out.println("Successfully added " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<City> cityList() {
        String sql2 = "SELECT * FROM city";
        List<City> cities = new ArrayList<>();
        try (Connection conn2 = Db.connect();
             Statement statement2 = conn2.createStatement();
             ResultSet resultSet2 = statement2.executeQuery(sql2)) {
            while (resultSet2.next()) {
                City city = new City();
                int id = resultSet2.getInt("id");
                String name = resultSet2.getString("name");
                city.setId(id);
                city.setName(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cities;
    }



    public static void getCityById(int id){
        String sql3 = "SELECT FROM city" +
                "WHERE id = ?";
        try(Connection conn3 = Db.connect();
        Statement statement3 = conn3.createStatement();
        ResultSet resultSet3 = statement3.executeQuery(sql3)){
            while(resultSet3.next()){
                System.out.println(resultSet3.getInt("id"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }





}


