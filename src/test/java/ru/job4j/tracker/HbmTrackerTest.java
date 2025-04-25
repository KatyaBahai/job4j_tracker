package ru.job4j.tracker;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HbmTrackerTest {
    private static HbmTracker tracker;

    @BeforeAll
    static void initTracker() throws Exception {
        tracker = new HbmTracker();
    }

    @AfterEach
    public void clearTheData() {
        tracker.findAll().forEach(item -> tracker.delete(item.getId()));
    }

    @AfterAll
    static void closeTracker() {
        if (tracker != null) {
            tracker.close();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenReplaceExistingItemThenItemIsReplaced() {
            Item item = new Item();
            item.setName("test2");
            tracker.add(item);
           Item replacingItem = new Item("test22");
            tracker.replace(item.getId(), replacingItem);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(replacingItem.getName());
    }

    @Test
    public void whenDeleteItemThenDeleted() {
            Item item = new Item();
            item.setName("test3");
            tracker.add(item);
            tracker.delete(item.getId());
            assertThat(tracker.findAll()).isEmpty();
    }

    @Test
    public void whenFindAllItemsThenAllFound() {
            Item item1 = new Item("test1");
            Item item2 = new Item("test2");
            Item item3 = new Item("test3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            List<Item> resultList = tracker.findAll();
            assertThat(resultList).containsExactly(item1, item2, item3);
    }

    @Test
    public void whenFindItemsByLikeNameThenFound() {
            Item item1 = new Item("test1");
            Item item2 = new Item("test2");
            Item item3 = new Item("test11");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            List<Item> resultList = tracker.findByName("1");
            assertThat(resultList).containsExactly(item1, item3);
    }

    @Test
    public void whenNoItemsWithThisNameThenNothingFound() {
            Item item1 = new Item("test1");
            Item item2 = new Item("test2");
            tracker.add(item1);
            tracker.add(item2);
            List<Item> resultList = tracker.findByName("bus");
            assertThat(resultList).isEmpty();
    }
}