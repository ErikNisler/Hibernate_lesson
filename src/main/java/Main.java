import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.math.BigDecimal;

public class Main{

    public static void main(String...args){
        ItemList itemList = new ItemList();

        System.out.println(itemList.loadItemById(2).toString());
        itemList.deleteAllOutOfStockItems();
        System.out.println(itemList.loadAllAvailableItems().toString());
        itemList.saveItem(new Item("200","190","Buchta","Povidlová",42, BigDecimal.valueOf(20.1)));
        itemList.updatePrice(3,BigDecimal.valueOf(123.2));
    }
}
