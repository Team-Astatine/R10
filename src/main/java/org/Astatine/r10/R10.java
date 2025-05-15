package org.Astatine.r10;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EnumSet;

public final class R10 extends JavaPlugin {

    private final String PLUGIN_NAME = "[R10]";
    private long RUNNING_TIME;

    @Override
    public void onLoad() {
        this.RUNNING_TIME = System.currentTimeMillis();
    }

    @Override
    public void onEnable() {
        registerCommandAndEvent();

        if (BooleanUtils.isFalse(getDataFolder().exists()))
            generationDataFile();

        dataFileLoader();

        configFileLoader();
        SchedulingExportData();

        pluginLoadTime();
    }

    @Override
    public void onDisable() {
        new RObjectIOHandler().exportData(
                DataFile.USER_DATA, new UserController().getAllUserTable(), getClass().getName()
        );

        new RObjectIOHandler().exportData(
                DataFile.KILL_STATUS, new UserKillStatusController().getAllUserTable(), getClass().getName()
        );

        ThreadPool.getThreadPool().allServiceOff();
        Bukkit.getScheduler().cancelTasks(this);
    }

    /**
     * @see UserDataCacheHolder 에 데이터를 모두 import 합니다.
     *
     * 데이터 파일 추가법.
     * @see DataFile 에 경로 및 데이터 명을 추가합니다.
     * resource 폴더에 {@link DataFile}에 추가했던 .json 파일을 생성합니다.
     * @see UserDataCacheHolder 에 해당 데이터 타입의 Caching Map 을 만듭니다.
     * Access Manager, Builder, Controller 를 생성합니다.
     * @see UserAccessManager
     * @see UserBuilder
     * @see UserController
     * 위 3개 클래스를 참조해 주세요.
     *
     */
    private void dataFileLoader() {
        new UserController().updateAllUserData(
                new RObjectIOHandler().importData(DataFile.USER_DATA, User.class, getClass().getName())
        );

        new UserKillStatusController().updateAllUserData(
                new RObjectIOHandler().importData(DataFile.KILL_STATUS, UserKillStatus.class, getClass().getName())
        );
    }

    /**
     * Config File 을 모두 load 합니다.
     * @see ConfigDataReload 시에 사용되는 함수입니다.
     * 플러그인 실행 시 최초 1회 실행됩니다.
     */
    public void configFileLoader() {
//        config Set
        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        configIOHandler.fileLoader();
        configIOHandler.allConfigLoad(); //config Load

//        Announcer Set
        Announcer.getAnnouncer().defaultAnnouncer();
    }

    /**
     * @see ListOfEvent Instance 를 생성합니다.
     * @see ListOfCommand 에 모든 커멘드 Instance 를 등록합니다.
     */
    private void registerCommandAndEvent() {
        getServer().getPluginManager().registerEvents(new ListOfEvent(), this);

        EnumSet.allOf(ListOfCommand.class).forEach(
                c -> getCommand(c.getCommand()).setExecutor(c.getCommandInstance())
        );
    }

    /**
     * @see UserDataCacheHolder 에 데이터를 모두 exeporting 합니다.
     *
     * @implSpec 12시간마다 자동으로 저장되는 scheduler 입니다.
     */
    private void SchedulingExportData() {
        long delay = 0;
        long interval = 720; // 12hour term auto save

        ThreadPool.getThreadPool().addSchedulingTaskMin(
                () -> {
                    new RObjectIOHandler().exportData(
                            DataFile.USER_DATA, new UserController().getAllUserTable(), getClass().getName()
                    );

                    new RObjectIOHandler().exportData(
                            DataFile.KILL_STATUS, new UserKillStatusController().getAllUserTable(), getClass().getName()
                    );

//                    new RObjectIOHandler().exportData(
//                            DataFile.USER_INVENTORY, new UserIn().getAllUserTable(), getClass().getName()
//                    );
                },
                delay,
                interval
        );
        Bukkit.getLogger().info(PLUGIN_NAME + " Success Add Scheduling All R01 Object Data Save");
    }

    private void pluginLoadTime() {
        String blockingTime = String.format("(%.3fs)",
                (System.currentTimeMillis() - this.RUNNING_TIME) / 1000.0
        );
        Bukkit.getLogger().info(PLUGIN_NAME + " Done " + blockingTime);
    }

    private void generationDataFile() {
        saveDefaultConfig(); // config Data
        saveResource(DataFile.USER_DATA.getFileName(), false); // userData
        saveResource(DataFile.KILL_STATUS.getFileName(), false); // killStatusData
        Bukkit.getLogger().info(PLUGIN_NAME + " Plugin Data File 생성 완료.");
    }
}
