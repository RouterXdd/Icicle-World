package ice.content;
import mindustry.world.meta.Attribute;

public class IceAttributes {

        public static Attribute sun, meth;

        public static void load() {
            sun = Attribute.add("sun");
            meth = Attribute.add("meth");
        }
}
