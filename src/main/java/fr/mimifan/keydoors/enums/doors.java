package fr.mimifan.keydoors.enums;

public enum doors {

    ACACIA_DOOR,DARK_OAK_DOOR,SPRUCE_DOOR,BIRCH_DOOR,CRIMSON_DOOR,WARPED_DOOR,IRON_DOOR,JUNGLE_DOOR,OAK_DOOR;

    public static doors findByName(String name) {
        doors result = null;
        for (doors door : values()) {
            if (door.name().equalsIgnoreCase(name)) {
                result = door;
                break;
            }
        }
        return result;
    }

    public static boolean exists(String name){
        for (doors door : values()){
            if(door.name().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

}
