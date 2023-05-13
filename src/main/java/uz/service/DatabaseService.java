package uz.service;

import org.springframework.stereotype.Service;
import uz.model.Country;
import uz.model.Result;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;
@Service
public class DatabaseService {
    private static final String url = "jdbc:postgresql://localhost:5432/country_crud";
    private static final String dbUser = "postgres";
    private static final String dbPassword = "root123";

    public Result addCountry(String name) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = getConnection(url, dbUser, dbPassword);
        String query = "{call add_country (?, ?, ?)}";
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.VARCHAR);

        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.executeUpdate();
        return new Result(callableStatement.getString(2), callableStatement.getBoolean(3));

    }


    public List<Country> getCountryList() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection =  getConnection(url, dbUser, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from country");
        List<Country> countries = new ArrayList<>();
        while (resultSet.next()){
            Country country = new Country(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
            countries.add(country);


        }
        return countries;
    }






}