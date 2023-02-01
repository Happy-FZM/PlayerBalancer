package com.jaimemartz.playerbalanceraddon;

import com.google.common.base.Strings;
import com.jaimemartz.playerbalanceraddon.util.Color;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    private final PlayerBalancerAddon plugin;

    public MainCommand(PlayerBalancerAddon plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spb")) {
            if (args.length != 0) {
                switch (args[0].toLowerCase()) {
                    case "connect": {
                        if (args.length >= 2) {
                            String input = args[1];
                            if (args.length >= 3) {
                                Player player = plugin.getServer().getPlayer(args[2]);
                                if (player != null) {
                                    plugin.getManager().connectPlayer(player, input);
                                    sender.sendMessage(Color.translate("&a成发送请求!"));
                                } else {
                                    sender.sendMessage(Color.translate("&c玩家不存在!"));
                                }
                            } else {
                                if (sender instanceof Player) {
                                    plugin.getManager().connectPlayer((Player) sender, input);
                                    sender.sendMessage(Color.translate("&a成发送请求!"));
                                } else {
                                    sender.sendMessage(Color.translate("&c只有玩家才能执行这条指令!"));
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "用法: /spb connect <组> [玩家]");
                        }
                        break;
                    }

                    case "fallback": {
                        if (args.length >= 2) {
                            Player player = plugin.getServer().getPlayer(args[1]);
                            if (player != null) {
                                plugin.getManager().fallbackPlayer((Player) sender);
                                sender.sendMessage(Color.translate("&a成发送请求!"));
                            } else {
                                sender.sendMessage(Color.translate("&c玩家不存在!"));
                            }
                        } else {
                            if (sender instanceof Player) {
                                plugin.getManager().fallbackPlayer((Player) sender);
                                sender.sendMessage(Color.translate("&a成发送请求!"));
                            } else {
                                sender.sendMessage(Color.translate("&c只有玩家才能执行这条指令!"));
                            }
                        }
                        break;
                    }

                    case "bypassconnect": {
                        if (args.length >= 2) {
                            String input = args[1];
                            if (args.length >= 3) {
                                Player player = plugin.getServer().getPlayer(args[2]);
                                if (player != null) {
                                    plugin.getManager().bypassConnect(player, input);
                                    sender.sendMessage(Color.translate("&a成发送请求!"));
                                } else {
                                    sender.sendMessage(Color.translate("&c玩家不存在!"));
                                }
                            } else {
                                if (sender instanceof Player) {
                                    plugin.getManager().bypassConnect((Player) sender, input);
                                    sender.sendMessage(Color.translate("&a成发送请求!"));
                                } else {
                                    sender.sendMessage(Color.translate("&c只有玩家才能执行这条指令!"));
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "用法: /spb bypassconnect <服务器> [玩家]");
                        }
                        break;
                    }

                    case "setbypass": {
                        if (args.length >= 2) {
                            Player player = plugin.getServer().getPlayer(args[1]);
                            if (player != null) {
                                plugin.getManager().setPlayerBypass(player);
                                sender.sendMessage(Color.translate("&a成发送请求!"));
                            } else {
                                sender.sendMessage(Color.translate("&c玩家不存在!"));
                            }
                        } else {
                            if (sender instanceof Player) {
                                plugin.getManager().setPlayerBypass((Player) sender);
                                sender.sendMessage(Color.translate("&a成发送请求!"));
                            } else {
                                sender.sendMessage(Color.translate("&c只有玩家才能执行这条指令!"));
                            }
                        }
                        break;
                    }

                    case "clearbypass": {
                        if (args.length >= 2) {
                            Player player = plugin.getServer().getPlayer(args[1]);
                            if (player != null) {
                                plugin.getManager().clearPlayerBypass((Player) sender);
                                sender.sendMessage(Color.translate("&a成发送请求!"));
                            } else {
                                sender.sendMessage(Color.translate("&c玩家不存在!"));
                            }
                        } else {
                            if (sender instanceof Player) {
                                plugin.getManager().clearPlayerBypass((Player) sender);
                                sender.sendMessage(Color.translate("&a成发送请求!"));
                            } else {
                                sender.sendMessage(Color.translate("&c只有玩家才能执行这条指令!"));
                            }
                        }
                        break;
                    }

                    case "overridestatus": {
                        if (args.length >= 3) {
                            if (args[2].equals("false") || args[2].equals("true")) {
                                plugin.getManager().setStatusOverride(args[1], Boolean.parseBoolean(args[2]));
                                sender.sendMessage(Color.translate("&a成发送请求!"));
                            } else {
                                sender.sendMessage(Color.translate("&c状态必须为 true 或 false !"));
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "用法: /spb overridestatus <服务器> <状态: false|true>");
                        }
                        break;
                    }

                    case "clearoverride": {
                        if (args.length >= 2) {
                            plugin.getManager().clearStatusOverride(args[1]);
                            sender.sendMessage(Color.translate("&a成发送请求!"));
                        } else {
                            sender.sendMessage(ChatColor.RED + "用法: /spb clearoverride <服务器>");
                        }
                        break;
                    }
                }
            } else {
                sender.sendMessage(ChatColor.STRIKETHROUGH + ChatColor.GRAY.toString() + Strings.repeat("-", 53));
                sender.sendMessage(ChatColor.GRAY + "可用指令:");
                sender.sendMessage(ChatColor.AQUA + "/spb connect <组> [玩家]" + ChatColor.GRAY + " - " + ChatColor.RED + "将自己或者其他玩家连接到某个组里");
                sender.sendMessage(ChatColor.AQUA + "/spb fallback [玩家]" + ChatColor.GRAY + " - " + ChatColor.RED + "将自己或者其他玩家连接到当前组的父级组里");
                sender.sendMessage(ChatColor.AQUA + "/spb bypassconnect <服务器> [玩家]" + ChatColor.GRAY + " - " + ChatColor.RED + "将自己或者指定玩家连接到特定服务器并且忽略人数平衡");
                sender.sendMessage(ChatColor.AQUA + "/spb setbypass [玩家]" + ChatColor.GRAY + " - " + ChatColor.RED + "设置自己或者指定玩家拥有忽略的特权");
                sender.sendMessage(ChatColor.AQUA + "/spb clearbypass [玩家]" + ChatColor.GRAY + " - " + ChatColor.RED + "移除自己或者指定玩家拥有忽略的特权");
                sender.sendMessage(ChatColor.AQUA + "/spb overridestatus <服务器> <状态: false|true>" + ChatColor.GRAY + " - " + ChatColor.RED + "更改指定服务器的可访问状态");
                sender.sendMessage(ChatColor.AQUA + "/spb clearoverride <服务器>" + ChatColor.GRAY + " - " + ChatColor.RED + "清除指定服务器的状态模式");
                sender.sendMessage(ChatColor.STRIKETHROUGH + ChatColor.GRAY.toString() + Strings.repeat("-", 53));

            }
        }
        return false;
    }
}
