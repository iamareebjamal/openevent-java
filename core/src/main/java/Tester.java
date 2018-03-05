import api.openevent.event.Event;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        Class<Event> eventClass = Event.class;

        List<Field> fields = Arrays.asList(eventClass.getDeclaredFields());
        for (Field field : fields) {
            System.out.println(field);
        }
    }

}
