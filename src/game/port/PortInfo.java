package game.port;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public interface PortInfo {

    String getName();

    String getArea();

    void investEconomy(int amount);

    int getEconomy();

    int getEconomyInvest();

    void investIndustry(int amount);

    int getIndustry();

    int getIndustryInvest();

    double getPriceIndex();

    int getPriceIndexPercentage();

    double setPriceIndex(double index);

}
