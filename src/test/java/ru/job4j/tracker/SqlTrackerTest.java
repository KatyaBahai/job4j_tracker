package ru.job4j.tracker;

import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenSaveItemAndFindByNameThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemFir = new Item("item");
        Item itemSec = new Item("item");
        Item itemThir = new Item("thing");
        tracker.add(itemFir);
        tracker.add(itemSec);
        tracker.add(itemThir);
        List<Item> expectedList = new ArrayList<>();
        expectedList.add(itemFir);
        expectedList.add(itemSec);
        assertThat(tracker.findByName(itemFir.getName())).isEqualTo(expectedList);
    }

    @Test
    public void whenSaveManyAndFinAllThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemFir = new Item("item");
        Item itemSec = new Item("piece");
        Item itemThir = new Item("thing");
        tracker.add(itemFir);
        tracker.add(itemSec);
        tracker.add(itemThir);
        List<Item> expectedList = new ArrayList<>();
        expectedList.add(itemFir);
        expectedList.add(itemSec);
        expectedList.add(itemThir);
        assertThat(tracker.findAll()).isEqualTo(expectedList);
    }

    @Test
    public void whenDeleteThenItemDeleted() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item itemSec = new Item("item");
        tracker.add(item);
        tracker.add(itemSec);
        List<Item> expectedList = new ArrayList<>();
        expectedList.add(itemSec);
        assertThat(tracker.delete(item.getId())).isTrue();
        assertThat(tracker.findAll()).isEqualTo(expectedList);
    }

    @Test
    public void whenItemReplaced() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Item newItem = new Item("thing");
        assertThat(tracker.replace(item.getId(), newItem)).isTrue();
    }

    @Test
    public void whenAddItemListContainsItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }
}