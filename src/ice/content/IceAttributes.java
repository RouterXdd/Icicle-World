package ice.content;
import mindustry.world.meta.Attribute;

public class IceAttributes {

        public static Attribute sun;

        public static void load() {
            sun = Attribute.add("sun");
        }
}
