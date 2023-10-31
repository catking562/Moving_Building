package taewookim.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import taewookim.Building;
import taewookim.MovingBuilding;
import taewookim.ObjectBuilding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CreateBuild implements CommandExecutor, TabCompleter {

    public boolean isDouble(String string) {
        try {
            Double.valueOf(string);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        switch(strings.length) {
            case 0:
                commandSender.sendMessage("/movingbuild create <Name>");
                commandSender.sendMessage("/movingbuild remove <Name>");
                commandSender.sendMessage("/movingbuild spawn <Name>");
                commandSender.sendMessage("/movingbuild edit <Key> <Value>");
                break;
            case 1:
                switch(strings[0]) {
                    case "create":
                        commandSender.sendMessage("/movingbuild create <Name>");
                        break;
                    case "remove":
                        commandSender.sendMessage("/movingbuild remove <Name>");
                        break;
                    case "edit":
                        commandSender.sendMessage("/movingbuild edit <Key> <Value>");
                        break;
                    case "spawn":
                        commandSender.sendMessage("/movingbuild spawn <Name>");
                        break;
                    default:
                        commandSender.sendMessage("?");
                        break;
                }
                break;
            case 2:
                switch(strings[0]) {
                    case "create":
                        if(commandSender instanceof Player p) {
                            if(MovingBuilding.target1.get(p)!=null&&MovingBuilding.target2.get(p)!=null) {
                                if(MovingBuilding.buildinglist.get(strings[1])!=null) {
                                    commandSender.sendMessage("해당이름의 구조물이 존재해서 생성불가");
                                }else {
                                    commandSender.sendMessage("생성중...");
                                    Building build = new Building(MovingBuilding.target1.get(p), MovingBuilding.target2.get(p));
                                    MovingBuilding.buildinglist.put(strings[1], build);
                                    commandSender.sendMessage("생성완료");
                                }
                            }else {
                                commandSender.sendMessage("나무도끼로 영역을 지정해주세요.");
                            }
                        }
                        break;
                    case "remove":
                        MovingBuilding.buildinglist.remove(strings[1]);
                        commandSender.sendMessage("삭제완료");
                        break;
                    case "edit":
                        commandSender.sendMessage("/movingbuild edit <Key> <Value>");
                        break;
                    case "spawn":
                        if(commandSender instanceof Player p) {
                            if(MovingBuilding.buildinglist.get(strings[1])!=null) {
                                commandSender.sendMessage("생성중...");
                                Location loc = p.getLocation();
                                ObjectBuilding building = new ObjectBuilding(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), 0, 0, 0, MovingBuilding.buildinglist.get(strings[1]));
                                MovingBuilding.addObjectBuilding(building);
                                MovingBuilding.selectobject.put(p, building);
                                commandSender.sendMessage("생성완료");
                            }else {
                                commandSender.sendMessage("존재하지 않음");
                            }
                        }
                        break;
                    default:
                        commandSender.sendMessage("?");
                        break;
                }
                break;
            default:
                switch(strings[0]) {
                    case "create":
                        if(commandSender instanceof Player p) {
                            if(MovingBuilding.target1.get(p)!=null&&MovingBuilding.target2.get(p)!=null) {
                                if(MovingBuilding.buildinglist.get(strings[1])!=null) {
                                    commandSender.sendMessage("해당이름의 구조물이 존재해서 생성불가");
                                }else {
                                    commandSender.sendMessage("생성중...");
                                    Building build = new Building(MovingBuilding.target1.get(p), MovingBuilding.target2.get(p));
                                    MovingBuilding.buildinglist.put(strings[1], build);
                                    commandSender.sendMessage("생성완료");
                                }
                            }else {
                                commandSender.sendMessage("나무도끼로 영역을 지정해주세요.");
                            }
                        }
                        break;
                    case "remove":
                        MovingBuilding.buildinglist.remove(strings[1]);
                        commandSender.sendMessage("삭제완료");
                        break;
                    case "edit":
                        if(commandSender instanceof Player p) {
                            if(MovingBuilding.selectobject.get(p)!=null&&MovingBuilding.selectobject.get(p).isExit()) {
                                switch(strings[1]) {
                                    case "x":
                                        if(isDouble(strings[2])) {
                                            MovingBuilding.selectobject.get(p).setX(Double.valueOf(strings[2]));
                                        }else {
                                            commandSender.sendMessage("숫자가 아님");
                                        }
                                        break;
                                    case "y":
                                        if(isDouble(strings[2])) {
                                            MovingBuilding.selectobject.get(p).setY(Double.valueOf(strings[2]));
                                        }else {
                                            commandSender.sendMessage("숫자가 아님");
                                        }
                                        break;
                                    case "z":
                                        if(isDouble(strings[2])) {
                                            MovingBuilding.selectobject.get(p).setZ(Double.valueOf(strings[2]));
                                        }else {
                                            commandSender.sendMessage("숫자가 아님");
                                        }
                                        break;
                                    case "yaw":
                                        if(isDouble(strings[2])) {
                                            MovingBuilding.selectobject.get(p).setYaw(Double.valueOf(strings[2]));
                                        }else {
                                            commandSender.sendMessage("숫자가 아님");
                                        }
                                        break;
                                    case "pitch":
                                        if(isDouble(strings[2])) {
                                            MovingBuilding.selectobject.get(p).setPitch(Double.valueOf(strings[2]));
                                        }else {
                                            commandSender.sendMessage("숫자가 아님");
                                        }
                                        break;
                                    case "roll":
                                        if(isDouble(strings[2])) {
                                            MovingBuilding.selectobject.get(p).setRoll(Double.valueOf(strings[2]));
                                        }else {
                                            commandSender.sendMessage("숫자가 아님");
                                        }
                                        break;
                                }
                                break;

                            }else {
                                commandSender.sendMessage("선택한 구조물이 없습니다.");
                            }
                        }
                        break;
                    case "spawn":
                        if(commandSender instanceof Player p) {
                            if(MovingBuilding.buildinglist.get(strings[1])!=null) {
                                commandSender.sendMessage("생성중...");
                                Location loc = p.getLocation();
                                ObjectBuilding building = new ObjectBuilding(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), 0, 0, 0, MovingBuilding.buildinglist.get(strings[1]));
                                MovingBuilding.addObjectBuilding(building);
                                MovingBuilding.selectobject.put(p, building);
                                commandSender.sendMessage("생성완료");
                            }else {
                                commandSender.sendMessage("존재하지 않음");
                            }
                        }
                        break;
                    default:
                        commandSender.sendMessage("?");
                        break;
                }
                break;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        switch(strings.length) {
            case 1:
                return Arrays.asList("create", "remove", "spawn", "edit");
            case 2:
                switch(strings[0]) {
                    case "remove":
                    case "spawn":
                        ArrayList<String> list = new ArrayList<>();
                        for(Map.Entry<String, Building> entry : MovingBuilding.buildinglist.entrySet()) {
                            list.add(entry.getKey());
                        }
                        return list;
                    case "edit":
                        return Arrays.asList("x", "y", "z", "yaw", "pitch", "roll");
                    case "create":
                        return Arrays.asList("<Name>");
                }
                break;
            case 3:
                if(strings[0].equalsIgnoreCase("edit")) {
                    return Arrays.asList("<숫자>");
                }
                break;
        }
        return Arrays.asList();
    }
}
