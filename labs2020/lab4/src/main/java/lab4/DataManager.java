package lab4;

public interface DataManager {
    String add(Item item);
    String delete(String title);
    String showPrice(String title);
    String changePrice(String title, int newPrice);
    String filterByPrice(int priceFrom, int priceTo);
    String showAll();

    DataManager getInstance();
}
