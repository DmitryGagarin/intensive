import org.example.service.HabitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HabitServiceTests {

    private HabitService habitService;

    @BeforeEach
    public void setUp() {
        habitService = new HabitService();
    }

    @Test
    public void testCreateHabit_one() {
        var result = habitService.createHabit("habit", "smoking", 3);
        assertThat(habitService.habits).hasSize(1);
    }

    @Test
    public void testCreateHabit_several() {
        var result = habitService.createHabit("habit", "smoking", 3);
        var result2 = habitService.createHabit("new habit", "fast food", 3);
        assertThat(habitService.habits).hasSize(2);
    }

    @Test
    public void testUpdateHabitName() {
        habitService.createHabit("habit", "smoking", 3);
        habitService.updateHabitName("habit", "new habit");
        assertThat(habitService.habits.get(0).getName()).isEqualTo("new habit");
    }

    @Test
    public void testUpdateHabitDescription() {
        habitService.createHabit("habit", "smoking", 3);
        habitService.updateHabitDescription("habit", "drinking");
        assertThat(habitService.habits.get(0).getDescription()).isEqualTo("drinking");
    }

    @Test
    public void testUpdateHabitInterval() {
        habitService.createHabit("habit", "smoking", 3);
        habitService.updateHabitInterval("habit", 5);
        assertThat(habitService.habits.get(0).getInterval()).isEqualTo(5);
    }

    @Test
    public void testDeleteHabit_one() {
        var result = habitService.createHabit("habit", "smoking", 3);
        habitService.deleteHabit("habit");
        assertThat(habitService.habits).hasSize(0);
    }

    @Test
    public void testDeleteHabit_several() {
        var result = habitService.createHabit("habit", "smoking", 3);
        var result1 = habitService.createHabit("habit1", "smoking1", 4);
        habitService.deleteHabit("habit");
        assertThat(habitService.habits).hasSize(1);
    }

    @Test
    public void testDeleteAll() {
        habitService.createHabit("habit", "smoking", 3);
        habitService.createHabit("habit1", "smoking1", 4);
        habitService.createHabit("habit2", "smoking2", 5);
        assertThat(habitService.habits).hasSize(3);
        habitService.deleteAllHabits();
        assertThat(habitService.habits).hasSize(0);
    }
}
