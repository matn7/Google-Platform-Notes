package design.patterns.flyweight;

public class TeslTank {
    private int x;
    private int y;
    private int hpLeft;
    private UnitStats stats;

    public TeslTank(int x, int y) {
        stats = UnitStatsRepository.getTeslaTankUnitStats();
        this.x = x;
        this.y = y;
        this.hpLeft = stats.getHp();
    }
}
