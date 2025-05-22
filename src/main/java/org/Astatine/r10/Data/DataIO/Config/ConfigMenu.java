package org.Astatine.r10.Data.DataIO.Config;

public enum ConfigMenu {
    MESSAGE_COMMAND_HELP("message.commandHelp"),

    MESSAGE_COMMAND_COMMUNITY("message.commandCommunity"),
    WORLD_SETTING_MOTD("world_setting.motd"),
    WORLD_SETTING_MINELIST("world_setting.minelist"),
    WORLD_SETTING_DISCORD("world_setting.discord"),
    WORLD_SETTING_NOTION("world_setting.notion"),

    MESSAGE_MINELIST_VOTE("message.mineListVote"),
    MESSAGE_DISCORD_INVITE("message.discordInvite"),
    MESSAGE_SERVER_GUIDE_NOTION("message.serverGuideNotion"),

    MESSAGE_COMMAND_SERVER_TIP("message.commandServerTip"),
    HEART_TIP("message.heartTip"),
    MESSAGE_EXTRA_HEART_TIP("message.extraHeartTip"),
    MESSAGE_STEEL_LIFE_TIP("message.steelLifeTip"),
    MESSAGE_RAID_TIP("message.raidTip"),
    MESSAGE_WEAPON_TIP("message.weaponTip"),
    MESSAGE_EXPLOSIVE_TIP("message.explosiveTip"),

    MESSAGE_COMMAND_TIP("message.CommandTip"),
    MESSAGE_COMMAND_FLY("message.commandFly"),
    MESSAGE_COMMAND_HAT("message.commandHat"),
    MESSAGE_COMMAND_SWAP("message.commandSwap"),
    MESSAGE_COMMAND_TOTEM("message.commandTotem"),
    MESSAGE_COMMAND_ENHANCE("message.commandEnhance"),
    MESSAGE_COMMAND_ANNOUNCING("message.commandAnnouncing"),
    MESSAGE_COMMAND_TPA("message.commandTpa"),
    MESSAGE_COMMAND_SHOP("message.commandShop"),
    MESSAGE_COMMAND_BALANCE("message.commandBalance"),
    MESSAGE_COMMAND_PARTY("message.commandParty"),
    MESSAGE_COMMAND_SKILL("message.commandSkill"),
    MESSAGE_COMMAND_PLAY_TIME("message.commandPlayTime");

    private final String configMessage;

    ConfigMenu(String configMessage) {
        this.configMessage = configMessage;
    }

    public String getConfigMessage() {
        return configMessage;
    }
}
