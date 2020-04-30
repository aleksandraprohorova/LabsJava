package lab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ProductItem implements Item {
    int id;
    int prodid;
    String title;
    int cost;

    static int idNumber = 1;

    public ProductItem(String title, int cost)
    {
        id = idNumber;
        prodid = id;
        this.title = title;
        this.cost = cost;

        idNumber++;
    }

    @Override
    public Collection<String> getData() {
        return new ArrayList<>(Arrays.asList(Integer.toString(id), Integer.toString(prodid), title, Integer.toString(cost)));
    }

    public String toString()
    {
        return id + " " + prodid + " " + title + " " + cost + "\n";
    }
}
