package ice.content;

import arc.graphics.Color;
import ice.graphics.IcePal;
import mindustry.game.Team;
import mindustry.graphics.Pal;

public class IceTeams {
    public static Team genesis, origin, methanite;

    public static void load() {
        genesis = newTeam(21, "genesis", IcePal.genesis);
        origin = newTeam(22, "origin", Pal.darkerMetal);
        methanite = newTeam(23, "methanite", IcePal.methaneLightish);
    }

    //modify any of 256 teams' properties
    private static Team newTeam(int id, String name, Color color) {
        Team team = Team.get(id);
        team.name = name;
        team.color.set(color);

        team.palette[0] = color;
        team.palette[1] = color.cpy().mul(0.75f);
        team.palette[2] = color.cpy().mul(0.5f);

        for(int i = 0; i < 3; i++){
            team.palettei[i] = team.palette[i].rgba();
        }

        return team;
    }
}
