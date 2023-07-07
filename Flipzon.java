import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
interface user{
    void browse_products(Admin one);
    void browse_deals(Admin one);
    void add_product_to_cart(double P_ID,int Quantity,Admin one);
    void add_deal_to_cart(int deal_num,int Quantity,Admin one);
    void view_coupon();
    void view_cart();
    void empty_cart();
    void checkout(Admin one);
    void account_balance();
    void upgrade_status(String status,Customer C);
    void add_amount(double amount);
}
class Customer implements user{
    String Name;
    String pswd;
    double wallet;
    int delivery_charge;
    boolean PRIME=false;
    boolean NORMAL=true;
    boolean ELITE=false;
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double wallet) {
        this.wallet += wallet;
    }
    Vector<Product> Product_cart=new Vector<>();
    Vector<Integer> Product_Quantity=new Vector<>();
    Vector<Deal> Deal_Cart=new Vector<>();
    Vector<Integer> Deal_Quantity=new Vector<>();
    Vector<Double> Coupons=new Vector<>();
    public Customer(String name,String pswd){
        this.Name=name;
        this.pswd=pswd;
        this.wallet=1000;
        this.delivery_charge=100;
    }
    @Override
    public void browse_products(Admin one) {
        if(one.Categories.size()==0){
            System.out.println("Dear "+this.Name+" ,there are no products for now. Please come again later");
            System.out.println();
        }
        else {
            for (Category C : one.Categories) {
                int i = 1;
                System.out.println("Category :: " + C.C_Name);
                for (Product P : C.Products) {
                    System.out.println("Product - " + i);
                    System.out.println("\tName :: " + P.P_Name);
                    System.out.println("\tID :: " + P.P_ID);
                    System.out.println("\tPrice :: " + P.Price);
                    System.out.println("\tStock Left :: " + P.Quantity);
                    System.out.println("\tDetails :: " + P.details);
                    System.out.println();
                    i++;
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    @Override
    public void browse_deals(Admin one) {
        if(one.Deals.size()==0){
            System.out.println("Dear "+this.Name+" ,there are no deals for now. Please come again later");
            System.out.println();
        }
        else {
            int i = 1;
            for (Deal D : one.Deals) {
                System.out.println("Deal - " + i);
                System.out.println("\tProduct 1: " + D.P1.P_Name);
                System.out.println("\tProduct 2: " + D.P2.P_Name);
                if (this.ELITE) {
                    System.out.println("\tDeal Price: " + D.price_E);
                }
                if (this.PRIME) {
                    System.out.println("\tDeal Price: " + D.price_P);
                }
                if (this.NORMAL) {
                    System.out.println("\tDeal Price: " + D.price_N);
                }
                if (D.P1.Quantity > D.P2.Quantity) {
                    System.out.println("\tStock Left: " + D.P2.Quantity);
                }
                else {
                    System.out.println("\tStock Left: " + D.P1.Quantity);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    @Override
    public void add_product_to_cart(double P_ID,int Quantity, Admin one) {
        System.out.println();
        int f=0;
        for(Category C: one.Categories){
            for (Product P: C.Products){
                if(P.P_ID==P_ID){
                    f=1;
                    if(P.Quantity>=Quantity){
                        this.Product_cart.add(P);
                        this.Product_Quantity.add(Quantity);
                        System.out.println("Product added successfully");
                        System.out.println();
                    }
                    else{
                        System.out.println("Not enough stock left");
                        System.out.println();
                    }
                }
            }
        }
        if(f==0){
            System.out.println("There is no product with given ID");
            System.out.println();
        }
    }
    @Override
    public void add_deal_to_cart(int deal_num,int Quantity, Admin one) {
        System.out.println();
        if(deal_num-1>one.Deals.size() || deal_num<1){System.out.println("Invalid Deal Number");}
        else {
            if (one.Deals.get(deal_num - 1).P1.Quantity >= Quantity && one.Deals.get(deal_num - 1).P2.Quantity >= Quantity) {
                this.Deal_Cart.add(one.Deals.get(deal_num - 1));
                this.Deal_Quantity.add(Quantity);
                System.out.println("Deal added to cart successfully");
                System.out.println();
            } else {
                System.out.println("Not enough stock left");
                System.out.println();
            }
        }
    }
    @Override
    public void view_coupon() {
        System.out.println();
        if(this.NORMAL){
            System.out.println("Please upgrade to PRIME or ELITE in order to win coupons");
            System.out.println();
        }
        if(this.PRIME || this.ELITE){
            if(this.Coupons.size()==0){
                System.out.println("You can win coupons on purchase of more than Rs5000");
                System.out.println();
            }
            else{
                System.out.println("You have won these coupons::");
                int i=1;
                for(Double d:this.Coupons){
                    System.out.println(i+")"+d+"%");
                    i++;
                }
                System.out.println();
                System.out.println("Keep shopping to win more coupons");
                System.out.println();
            }
        }
    }
    @Override
    public void view_cart() {
        System.out.println();
        if(this.Product_cart.size()>0) {
            System.out.println("Products in Cart::");
            int i = 1;
            for (Product P : this.Product_cart) {
                System.out.println("Product - " + i);
                System.out.println("\tName:: " + P.P_Name);
                System.out.println("\tPrice:: " + P.Price);
                System.out.println("\tQuantity:: " + this.Product_Quantity.get(i - 1));
                System.out.println();
                i++;
            }
            System.out.println();
        }
        if(this.Deal_Cart.size()>0) {
            System.out.println();
            System.out.println("Deals in Cart::");
            int j = 1;
            for (Deal D : this.Deal_Cart) {
                System.out.println("Deal - " + j);
                System.out.println("\tName of Product1:: " + D.P1.P_Name);
                System.out.println("\tName of Product2:: " + D.P2.P_Name);
                if (this.ELITE) System.out.println("\tPrice:: " + D.price_E);
                if (this.PRIME) System.out.println("\tPrice:: " + D.price_P);
                if (this.NORMAL) System.out.println("\tPrice:: " + D.price_N);
                System.out.println("\tQuantity:: " + this.Deal_Quantity.get(j - 1));
                System.out.println();
                j++;
            }
            System.out.println();
        }
        if(this.Product_cart.size()==0 && this.Deal_Cart.size()==0){
            System.out.println();
            System.out.println("Cart Is Empty");
            System.out.println("Please add some products or deals first to view them in cart");
            System.out.println();
        }
    }
    @Override
    public void empty_cart() {
        this.Deal_Cart.clear();
        this.Product_cart.clear();
        this.Product_Quantity.clear();
        this.Deal_Quantity.clear();
        System.out.println("Cart Successfully emptied");
        System.out.println();
    }
    @Override
    public void checkout(Admin one) {
        System.out.println();
        System.out.println("Proceeding to checkout. Details:");
        System.out.println();
        if(Product_cart.isEmpty() && Deal_Cart.isEmpty()){
            System.out.println("Cart is Empty");
        }
        else {
            double products_cost = 0;
            double deals_cost = 0;
            int i = 1;
            double discount = 0;
            int ind=0;
            for (Product P : this.Product_cart) {
                System.out.println();
                System.out.println("Product - " + i);
                System.out.println("Product Name :: " + P.P_Name);
                System.out.println("Product ID :: " + P.P_ID);
                System.out.println("Quantity :: " + this.Product_Quantity.get(i - 1));
                System.out.println("Price :: " + P.Price * this.Product_Quantity.get(i - 1));
                System.out.println();
                products_cost += P.Price * this.Product_Quantity.get(i - 1);
                if (this.ELITE) {
                    double max = 10;
                    for (int g = 0; g < this.Coupons.size(); g++) {
                        if (this.Coupons.get(g) > max) {
                            max = this.Coupons.get(g);
                            ind = g;
                        }
                    }
                    if (P.dis_E > max) {
                        System.out.println();
                        System.out.println("Discount :: " + P.dis_E + "% of " + (P.Price * this.Product_Quantity.get(i - 1)) + " = " + (P.Price * this.Product_Quantity.get(i - 1)) * P.dis_E / 100);
                        discount += (P.Price * this.Product_Quantity.get(i - 1)) * P.dis_E / 100;
                    } else {
                        System.out.println();
                        System.out.println("Discount :: " + max + "% of " + (P.Price * this.Product_Quantity.get(i - 1)) + " = " + (P.Price * this.Product_Quantity.get(i - 1)) * max / 100);
                        discount += (P.Price * this.Product_Quantity.get(i - 1)) * max / 100;
                    }
                }
                if (this.PRIME) {
                    double max = 5;
                    for (int g = 0; g < this.Coupons.size(); g++) {
                        if (this.Coupons.get(g) > max) {
                            max = this.Coupons.get(g);
                            ind = g;
                        }
                    }
                    if (P.dis_P > max) {
                        System.out.println();
                        System.out.println("Discount :: " + P.dis_P + "% of " + (P.Price * this.Product_Quantity.get(i - 1)) + " = " + (P.Price * this.Product_Quantity.get(i - 1)) * P.dis_P / 100);
                        discount += (P.Price * this.Product_Quantity.get(i - 1)) * P.dis_P / 100;
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("Discount :: " + max + "% of " + (P.Price * this.Product_Quantity.get(i - 1)) + " = " + (P.Price * this.Product_Quantity.get(i - 1)) * max / 100);
                        discount += (P.Price * this.Product_Quantity.get(i - 1)) * max / 100;
                        System.out.println();
                    }
                }
                if (this.NORMAL) {
                    System.out.println();
                    System.out.println("Discount :: " + P.dis_N + "% of " + (P.Price * this.Product_Quantity.get(i - 1)) + " = " + (P.Price * this.Product_Quantity.get(i - 1)) * P.dis_N / 100);
                    discount += (P.Price * this.Product_Quantity.get(i - 1)) *  P.dis_N  / 100;
                    System.out.println();
                }
                System.out.println();
                i++;
            }
            int j = 1;
            for (Deal D : this.Deal_Cart) {
                System.out.println();
                System.out.println("Deal - " + j);
                System.out.println("Product 1 in Deal :: " + D.P1.P_Name);
                System.out.println("Product 2 in Deal :: " + D.P2.P_Name);
                System.out.println("Quantity :: " + this.Deal_Quantity.get(j - 1));
                if (this.ELITE) System.out.println("Price :: Rs" + (D.price_E * this.Deal_Quantity.get(j - 1)));
                if (this.PRIME) System.out.println("Price :: Rs" + (D.price_P * this.Deal_Quantity.get(j - 1)));
                if (this.NORMAL) System.out.println("Price :: Rs" + (D.price_N * this.Deal_Quantity.get(j - 1)));
                System.out.println();
                if (this.ELITE) deals_cost += (D.price_E * this.Deal_Quantity.get(j - 1));
                if (this.PRIME) deals_cost += (D.price_P * this.Deal_Quantity.get(j - 1));
                if (this.NORMAL) deals_cost += (D.price_N * this.Deal_Quantity.get(j - 1));
                j++;
            }
            double total = products_cost + deals_cost;
            double delivery = 0;
            if (this.ELITE) {
                System.out.println();
                System.out.println("Delivery Charge :: Rs" + this.delivery_charge);
                delivery = this.delivery_charge;
            }
            if (this.PRIME) {
                System.out.println();
                System.out.println("Delivery Charge :: RS" + this.delivery_charge + " + 2% of " + total + " = Rs" + (100 + 0.02 * total));
                delivery = 100 + 0.02 * total;
            }
            if (this.NORMAL) {
                System.out.println();
                System.out.println("Delivery Charge :: RS" + this.delivery_charge + " + 5% of " + total + " = Rs" + (100 + 0.05 * total));
                delivery = 100 + 0.05 * total;
            }
            double Cost = total - discount + delivery;
            System.out.println();
            System.out.println("Total Cost = " + Cost);
            System.out.println();
            if (Cost > this.getWallet()) {
                System.out.println();
                System.out.println("Not Enough Money In Wallet, Cannot Place The Order");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("Order Placed");
                System.out.println();
                if (this.ELITE) System.out.println("Your Order will be delivered within 2 days");
                if (this.PRIME) System.out.println("Your Order will be delivered within 3-6 days");
                if (this.NORMAL) System.out.println("Your Order will be delivered within 7-10 days");
                System.out.println();
                this.setWallet(-Cost);
                if(this.Coupons.size()>0) {
                    this.Coupons.remove(ind);
                }
                for(Category C:one.Categories){
                    for(Product P1:C.Products){
                        for(Product P2: this.Product_cart){
                            if(P1.P_ID==P2.P_ID){
                                P1.Quantity-=this.Product_Quantity.get(this.Product_cart.indexOf(P2));
                            }
                        }
                    }
                }
                for(Deal D1: one.Deals){
                    for(Deal D2:this.Deal_Cart){
                        if(D1==D2){
                            D1.P1.Quantity-=this.Deal_Quantity.get((this.Deal_Cart.indexOf(D2)));
                            D1.P2.Quantity-=this.Deal_Quantity.get((this.Deal_Cart.indexOf(D2)));
                        }
                    }
                }
                this.Product_cart.clear();
                this.Deal_Cart.clear();
                this.Product_Quantity.clear();
                this.Deal_Quantity.clear();
                if (Cost > 5000 && (this.ELITE || this.PRIME)) {
                    if (this.ELITE) {
                        int x = ThreadLocalRandom.current().nextInt(3, 5);
                        System.out.print("You have won " + x + " coupons of ");
                        for (int k = 0; k < x - 1; k++) {
                            int y = ThreadLocalRandom.current().nextInt(5, 16);
                            this.Coupons.add((double) y);
                            System.out.print(y + "% , ");
                        }
                        int g = ThreadLocalRandom.current().nextInt(5, 16);
                        this.Coupons.add((double) g);
                        System.out.print(g + "% discount . Congratulations!!");
                    }
                    System.out.println();
                    if (this.PRIME) {
                        int x = ThreadLocalRandom.current().nextInt(1, 3);
                        System.out.print("You have won " + x + " coupons of ");
                        for (int k = 0; k < x - 1; k++) {
                            int y = ThreadLocalRandom.current().nextInt(5, 16);
                            this.Coupons.add((double) y);
                            System.out.print(y + "% , ");
                        }
                        int g = ThreadLocalRandom.current().nextInt(5, 16);
                        this.Coupons.add((double) g);
                        System.out.print(g + "% discount . Congratulations!!");
                    }
                    System.out.println();
                }
            }
        }
    }
    @Override
    public void account_balance() {
        System.out.print("Current Account Balance = "+this.getWallet());
        System.out.println();
    }
    @Override
    public void upgrade_status(String status,Customer C) {
        if(this.PRIME && Objects.equals(status,"NORMAL")){
            this.NORMAL=true;
            this.PRIME=false;
            this.ELITE=false;
            this.setWallet(200);
            System.out.println("Status Downgraded to NORMAL");
            System.out.println();
        }
        if(this.ELITE && Objects.equals(status,"NORMAL")){
            this.NORMAL=true;
            this.PRIME=false;
            this.ELITE=false;
            this.setWallet(300);
            System.out.println("Status Downgraded to NORMAL");
            System.out.println();
        }
        if(this.ELITE && Objects.equals(status,"PRIME")){
            this.NORMAL=true;
            this.PRIME=false;
            this.ELITE=false;
            this.setWallet(100);
            System.out.println("Status Downgraded to PRIME");
            System.out.println();
            C=new Prime(this.Name,this.pswd);
        }
        if(this.PRIME && Objects.equals(status, "ELITE")){
            if(this.getWallet()>=100){
                this.ELITE=true;
                this.NORMAL=false;
                this.PRIME=false;
                System.out.println("Status Upgraded to ELITE");
                System.out.println();
                C=new Elite(this.Name,this.pswd);
                this.setWallet(-100);
            }
            else{
                System.out.println("Not enough money in account");
                System.out.println();
            }
        }
        if(this.NORMAL && Objects.equals(status, "ELITE")){
            if(this.getWallet()>=300){
                this.ELITE=true;
                this.NORMAL=false;
                this.PRIME=false;
                System.out.println("Status Upgraded to ELITE");
                System.out.println();
                this.setWallet(-300);
                C=new Elite(this.Name,this.pswd);
            }
            else{
                System.out.println("Not enough money in account");
                System.out.println();
            }
        }
        if(this.NORMAL && Objects.equals(status, "PRIME")){
            if(this.getWallet()>=200){
                this.PRIME=true;
                this.NORMAL=false;
                this.ELITE=false;
                System.out.println("Status Upgraded to PRIME");
                System.out.println();
                this.setWallet(-200);
                C=new Prime(this.Name,this.pswd);
            }
            else{
                System.out.println("Not enough money in account");
                System.out.println();
            }
        }
    }
    @Override
    public void add_amount(double amount) {
        this.setWallet(amount);
        System.out.println("Amount added successfully");
        System.out.println("Current Account Balance = "+ this.getWallet());
        System.out.println();
    }
}
class Elite extends  Customer{
    public Elite(String name, String pswd) {
        super(name, pswd);
        this.wallet=super.wallet;
        this.Product_cart=super.Product_cart;
        this.Coupons=super.Coupons;
        this.Product_Quantity=super.Product_Quantity;
        this.Deal_Cart=super.Deal_Cart;
        this.Deal_Quantity=super.Deal_Quantity;
        this.ELITE=true;
    }
    double wallet;
    Vector<Product> Product_cart;
    Vector<Integer> Product_Quantity;
    Vector<Deal> Deal_Cart;
    Vector<Integer> Deal_Quantity;
    Vector<Double> Coupons;
}
class Prime extends Customer{
    public Prime(String name, String pswd) {
        super(name, pswd);
        this.wallet=super.wallet;
        this.Product_cart=super.Product_cart;
        this.Coupons=super.Coupons;
        this.Product_Quantity=super.Product_Quantity;
        this.Deal_Cart=super.Deal_Cart;
        this.Deal_Quantity=super.Deal_Quantity;
        this.PRIME=true;
    }
    double wallet;
    Vector<Product> Product_cart;
    Vector<Integer> Product_Quantity;
    Vector<Deal> Deal_Cart;
    Vector<Integer> Deal_Quantity;
    Vector<Double> Coupons;
}
interface admins{
    void add_category(String name,int C_ID);
    void delete_category(int C_ID);
    void add_product(int C_ID,String name,double P_ID,double price,int quantity,String details);
    void delete_product(double P_ID);
    void discount_product(double P_ID,double dis_E,double dis_P,double dis_N);
    void giveaway_deal(double P_ID1,double P_ID2,double E,double P,double N);
}
class Admin implements admins{
    String Username;
    String password;
    Vector<Category> Categories=new Vector<>();
    Vector<Deal> Deals=new Vector<>();
    public Admin(String name,String pswd){
        this.Username=name;
        this.password=pswd;
    }
    @Override
    public void add_category(String name, int C_ID) {
        Category C=new Category(name,C_ID);
        System.out.println("Category added successfully");
        System.out.println();
        Categories.add(C);
    }
    @Override
    public void delete_category(int C_ID) {
        Categories.removeIf(C -> C.C_ID == C_ID);
        System.out.println("Category deleted successfully");
        System.out.println();
    }
    @Override
    public void add_product(int C_ID,String name,double P_ID,double price,int quantity,String details) {
        for(Category C:Categories){
            if(C.C_ID==C_ID){
                Product P=new Product(name,P_ID,price,quantity,details);
                C.Products.add(P);
                System.out.println("Product added successfully");
                System.out.println();
                break;
            }
        }
    }
    @Override
    public void delete_product(double P_ID) {
        for(Category C:Categories){
            C.Products.removeIf(P -> P.P_ID == P_ID);
            System.out.println("Product deleted successfully");
            System.out.println();
            break;
        }
    }
    @Override
    public void discount_product(double P_ID, double dis_E, double dis_P, double dis_N) {
        for(Category C:Categories){
            for(Product P: C.Products){
                if(P.P_ID==P_ID){
                    P.dis_E=dis_E;
                    P.dis_P=dis_P;
                    P.dis_N=dis_N;
                    System.out.println("Discount given on product with ID:"+P_ID);
                    System.out.println();
                    break;
                }
            }
        }
    }
    @Override
    public void giveaway_deal(double P_ID1, double P_ID2, double E, double P, double N) {
        Product P1=null;
        Product P2=null;
        for(Category C:Categories){
            for(Product Pro:C.Products){
                if(Pro.P_ID==P_ID1){P1=Pro;}
                if(Pro.P_ID==P_ID2){P2=Pro;}
            }
        }
        Deal D=new Deal(P1,P2,E,P,N);
        Deals.add(D);
        System.out.println("Product with ID:"+P_ID1+" & Product with ID:"+P_ID2+" added to Deals");
        System.out.println();
    }
}
class Category{
    String C_Name;
    int C_ID;
    Vector<Product> Products=new Vector<>();
    public Category(String name,int ID){
        this.C_Name=name;
        this.C_ID=ID;
    }
}
class Product{
    String P_Name;
    double P_ID;
    double Price;
    int Quantity;
    String details;
    public Product(String name,double ID,double price,int quantity,String details){
        this.P_Name=name;
        this.P_ID=ID;
        this.Price=price;
        this.Quantity=quantity;
        this.details=details;
    }
    double dis_P;
    double dis_E;
    double dis_N;
}
class Deal{
    Product P1;
    Product P2;
    double price_E;
    double price_P;
    double price_N;
    public Deal(Product P1,Product P2,double E,double P,double N){
        this.P1=P1;
        this.P2=P2;
        this.price_E=E;
        this.price_P=P;
        this.price_N=N;
    }
}
public class Flipzon {
    public static void MainAdmin(Scanner S,Vector <Customer> Customers,Admin one) {
        System.out.println();
        System.out.println("WELCOME TO FLIPZON");
        System.out.println();
        System.out.println("1) Enter as Admin");
        System.out.println("2) Explore the Product Catalog");
        System.out.println("3) Show Available Deals");
        System.out.println("4) Enter as Customer");
        System.out.println("5) Exit the Application");
        System.out.println();
        System.out.print("Enter Your Choice :: ");
        int n = S.nextInt();
        if(n==1){AdminEntry(S,Customers,one);}
        else if(n==2){
            System.out.println();
            if(one.Categories.size()==0){
                System.out.println("Dear User, there are no products for now!!! Please check regularly for exciting products");
            }
            else {
                for (Category C : one.Categories) {
                    System.out.println("Category :: " + C.C_Name);
                    int i = 1;
                    for (Product P : C.Products) {
                        if(P.Quantity>0){
                            System.out.println("Product - " + i);
                            System.out.println("\tName:: " + P.P_Name);
                            System.out.println("\tID:: " + P.P_ID);
                            System.out.println("\tPrice:: " + P.Price);
                            System.out.println("\tQuantity:: " + P.Quantity);
                            System.out.println("\tDetails:: " + P.details);
                            System.out.println();
                            i++;
                        }
                    }
                }
            }
            MainAdmin(S,Customers,one);
        }
        else if(n==3){
            System.out.println();
            int i=1;
            if(one.Deals.size()==0){
                System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals");
            }
            for(Deal D: one.Deals){
                int quantity = Math.min(D.P1.Quantity, D.P2.Quantity);
                if(quantity>0){
                    System.out.println("Deal - "+i);
                    System.out.println("\tProduct 1::"+D.P1.P_Name);
                    System.out.println("\tProduct 1::"+D.P2.P_Name);
                    System.out.println("\tDeal Price for Elite::"+D.price_E);
                    System.out.println("\tDeal Price for Prime::"+D.price_P);
                    System.out.println("\tDeal Price for Normal::"+D.price_N);
                    System.out.println("\tQuantity::"+quantity);
                    System.out.println();
                    i++;
                }
            }
            MainAdmin(S,Customers,one);
        }
        else if(n==4){MainEntry(S,Customers,one);}
        else if(n==5){
            System.out.println();
            System.out.println("Thank You For Using FlipZon");
            System.exit(0);
        }
        else{
            System.out.println();
            System.out.println("Invalid Input");
            MainAdmin(S,Customers,one);
        }
    }
    public static void AdminEntry(Scanner S,Vector <Customer> Customers,Admin one){
        System.out.println();
        System.out.println("Dear Admin,");
        System.out.println("Please enter your username and password");
        System.out.println();
        System.out.print("Enter Name:: ");
        String fname=S.next();
        String lname=S.next();
        String name=fname+" "+lname;
        System.out.print("Enter Password:: ");
        String pass=S.next();
        if(Objects.equals(one.Username, name) && Objects.equals(one.password, pass)){
            System.out.println("Login Successful");
            System.out.println();
            adminproperties(S,Customers,one);
        }
        else{
            System.out.println("Invalid Username OR Password");
        }
        MainAdmin(S,Customers,one);
    }
    public static double id_check_product(Scanner S,Admin one,double i){
        double id=0;
        for(Category c:one.Categories){
            for(Product P:c.Products){
                if(P.P_ID==i){
                    System.out.println("Product with similar ID already exists, Please add unique ID");
                    System.out.print("Enter Product ID :: ");
                    i=S.nextDouble();
                    id_check_product(S,one,i);
                }
                else{
                    id=i;
                    break;
                }
            }
        }
        return id;
    }
    public static int id_check_category(Scanner S,Admin one,int i){
        int id=0;
        for(Category C:one.Categories){
            if(C.C_ID==i){
                System.out.println("Category with similar ID already exists, Please provide unique ID");
                System.out.print("Enter ID :: ");
                i=S.nextInt();
                id_check_category(S,one,i);
            }
            else{
                id=i;
            }
        }
        return id;
    }
    public static void adminproperties(Scanner S,Vector <Customer> Customers,Admin one) {
        System.out.println("Welcome " + one.Username + " !!!!");
        System.out.println();
        System.out.println("Please choose any one of the following actions:");
        System.out.println();
        System.out.println("1) Add category");
        System.out.println("2) Delete category");
        System.out.println("3) Add Product");
        System.out.println("4) Delete Product");
        System.out.println("5) Set Discount on Product");
        System.out.println("6) Add giveaway deal");
        System.out.println("7) Back");
        System.out.println();
        System.out.print("Enter Your Choice::");
        int t = S.nextInt();
        if(t==1){
            System.out.println();
            System.out.print("Enter ID :: ");
            int id=S.nextInt();
            id=id_check_category(S,one,id);
            System.out.print("Enter Name :: ");
            String name=S.next();
            one.add_category(name,id);
            System.out.println();
            System.out.println("Add a Product >");
            System.out.println();
            System.out.print("Enter Product Name :: ");
            String n=S.next();
            System.out.print("Enter Product ID :: ");
            double i=S.nextDouble();
            System.out.print("Enter Product Price :: ");
            double p=S.nextDouble();
            System.out.print("Enter Product Quantity :: ");
            int q=S.nextInt();
            System.out.println("Enter Product Details :: ");
            String det=S.nextLine();
            det=S.nextLine();
            one.add_product(id,n,i,p,q,det);
            adminproperties(S,Customers,one);
        }
        else if(t==2){
            System.out.println();
            System.out.print("Enter ID :: ");
            int id=S.nextInt();
            int f=0;
            int g=0;
            Vector<Customer> delete_c=new Vector<>();
            Vector<Product> delete_p=new Vector<>();
            for(Category C: one.Categories){
                if(C.C_ID==id){
                    f=1;
                    for(Customer c:Customers){
                        for(Product p:c.Product_cart){
                            if(C.Products.contains(p)){
                                delete_c.add(c);
                                delete_p.add(p);
                                g=1;
                            }
                        }
                    }
                }
            }
            if(g==1){
                for(Customer c:delete_c){
                    for(Product p:delete_p){
                        if(c.Product_cart.contains(p)){
                            c.Product_Quantity.remove(c.Product_cart.indexOf(p));
                            c.Product_cart.remove(p);
                        }
                    }
                }
            }
            if(f==1){
                one.delete_category(id);
                adminproperties(S,Customers,one);
            }
            if(f==0){
                System.out.println("There is no category with given ID");
            }
            adminproperties(S,Customers,one);
        }
        else if(t==3){
            System.out.println();
            System.out.print("Enter Category ID::");
            int id=S.nextInt();
            int f=0;
            for(Category C: one.Categories){
                if(C.C_ID==id){
                    f=1;
                    System.out.print("Enter Product Name :: ");
                    String n=S.next();
                    System.out.print("Enter Product ID :: ");
                    double i=S.nextDouble();
                    i=id_check_product(S,one,i);
                    System.out.print("Enter Product Price :: ");
                    double p=S.nextDouble();
                    System.out.print("Enter Product Quantity :: ");
                    int q=S.nextInt();
                    System.out.println("Enter Product Details :: ");
                    String det=S.nextLine();
                    det=S.nextLine();
                    one.add_product(id,n,i,p,q,det);
                    adminproperties(S,Customers,one);
                }
            }
            if(f==0){
                System.out.println("There is no category with given ID");
                adminproperties(S,Customers,one);
            }
        }
        else if(t==4){
            System.out.println();
            System.out.print("Enter Product ID :: ");
            double i=S.nextDouble();
            Vector<Customer> delete_c=new Vector<>();
            Vector<Product> delete_p=new Vector<>();
            int f=0;
            int g=0;
            for(Category C:one.Categories){
                for(Product P:C.Products){
                    if(P.P_ID==i){
                        f=1;
                        for(Customer c:Customers){
                            for(Product p:c.Product_cart){
                                if(P==p){
                                    g=1;
                                    delete_c.add(c);
                                    delete_p.add(p);
                                }
                            }
                        }
                    }
                }
            }
            if(g==1){
                for(Customer c:delete_c){
                    for (Product p:delete_p){
                        if(c.Product_cart.contains(p)){
                            c.Product_Quantity.remove(c.Product_cart.indexOf(p));
                            c.Product_cart.remove(p);
                        }
                    }
                }
            }
            if(f==1){
                one.delete_product(i);
                adminproperties(S,Customers,one);
            }
            if(f==0){
                System.out.println("There is no product with given ID");
                adminproperties(S,Customers,one);
            }
        }
        else if(t==5){
            System.out.println();
            System.out.print("Enter Product ID::");
            double id=S.nextDouble();
            int f=0;
            for(Category C: one.Categories){
                for(Product P:C.Products){
                    if(P.P_ID==id){
                        f=1;
                        System.out.print("Enter discount in % for ELITE::");
                        double e=S.nextDouble();
                        System.out.print("Enter discount in % for PRIME::");
                        double p=S.nextDouble();
                        System.out.print("Enter discount in % for NORMAL::");
                        double n=S.nextDouble();
                        one.discount_product(id,e,p,n);
                        adminproperties(S,Customers,one);
                    }
                }
            }
            if(f==0){
                System.out.println("There is no product with given ID");
                adminproperties(S,Customers,one);
            }
        }
        else if(t==6){
            System.out.println();
            System.out.print("Enter Product ID 1::");
            double p1=S.nextDouble();
            System.out.print("Enter Product ID 1::");
            double p2=S.nextDouble();
            int f1=0,f2=0;
            Product pro1=null,pro2=null;
            for(Category C: one.Categories){
                for(Product P:C.Products){
                    if(P.P_ID==p1){
                        f1=1;
                        pro1=P;
                    }
                    if(P.P_ID==p2){
                        f2=1;
                        pro2=P;
                    }
                }
            }
            if(f1==1 && f2==1){
                double price=pro1.Price+pro2.Price;
                System.out.println();
                System.out.print("Enter Price for Elite::");
                double e=S.nextDouble();
                while(e>=price){
                    System.out.println("Deal price should be less than combined Price");
                    System.out.println();
                    System.out.print("Enter Price for Elite::");
                    e=S.nextDouble();
                }
                System.out.print("Enter Price for Prime::");
                double p=S.nextDouble();
                while(p>=price){
                    System.out.println("Deal price should be less than combined Price");
                    System.out.println();
                    System.out.print("Enter Price for Prime::");
                    p=S.nextDouble();
                }
                System.out.print("Enter Price for Normal::");
                double n=S.nextDouble();
                while(n>=price){
                    System.out.println("Deal price should be less than combined Price");
                    System.out.println();
                    System.out.print("Enter Price for Normal::");
                    n=S.nextDouble();
                }
                one.giveaway_deal(p1,p2,e,p,n);
            }
            else{
                System.out.println("There is no product with the given ID");
            }
            adminproperties(S,Customers,one);
        }
        else if(t==7){
            System.out.println();
            MainAdmin(S,Customers,one);
        }
        else{
            System.out.println();
            System.out.println("Invalid Input");
            adminproperties(S,Customers,one);
        }
    }
    public static void MainEntry(Scanner S,Vector <Customer> Customers,Admin one){
        System.out.println("1) Sign up");
        System.out.println("2) Log in");
        System.out.println("3) Back");
        System.out.println();
        System.out.print("Enter Your Choice :: ");
        int o=S.nextInt();
        System.out.println();
        if(o==1){SignUp(S,Customers,one);}
        else if(o==2){LogIn(S,Customers,one);}
        else if(o==3){MainAdmin(S,Customers,one);}
        else{
            System.out.println("Invalid Input");
            MainEntry(S,Customers,one);
        }
    }
    public static void SignUp(Scanner S,Vector <Customer> Customers,Admin one){
        System.out.println();
        System.out.print("Enter Name : ");
        String F_name=S.next();
        String L_name=S.next();
        String name=F_name+" "+L_name;
        System.out.print("Enter Password : ");
        String pwd=S.next();
        Customer C=new Customer(name,pwd);
        Customers.add(C);
        System.out.println("Registered Successfully");
        MainEntry(S,Customers,one);
    }
    public static void LogIn(Scanner S, Vector <Customer> Customers, Admin one){
        System.out.println();
        System.out.print("Enter Name :: ");
        String F_name=S.next();
        String L_name=S.next();
        System.out.print("Enter Password :: ");
        String name=F_name+" "+L_name;
        String pwd=S.next();
        int flag=0;
        for (Customer customer : Customers) {
            if (Objects.equals(customer.Name, name) && Objects.equals(customer.pswd, pwd)) {
                flag = 1;
                System.out.println("Login Successful");
                System.out.println();
                System.out.println("Welcome " + customer.Name);
                lev1(S, customer,Customers,one);
            }
        }
        if(flag==0){
            System.out.println("User Not Registered -|OR|- Entered Wrong Details");
        }
        MainEntry(S,Customers,one);
    }
    public static void lev1(Scanner S,Customer C,Vector <Customer> Customers,Admin one) {
        System.out.println();
        System.out.println("1) browse products");
        System.out.println("2) browse Deal");
        System.out.println("3) add a product to cart");
        System.out.println(" 4) add products in deal to cart");
        System.out.println("5) view coupons");
        System.out.println(" 6) check account balance");
        System.out.println("7) view cart");
        System.out.println("8) empty cart");
        System.out.println(" 9) checkout cart");
        System.out.println("10) upgrade customer status");
        System.out.println("11) Add amount to wallet");
        System.out.println("12) back");
        System.out.println();
        System.out.print("Enter Your Choice :: ");
        int y = S.nextInt();
        if(y==1){
            System.out.println();
            C.browse_products(one);
            lev1(S,C,Customers,one);
        }
        else if(y==2){
            System.out.println();
            C.browse_deals(one);
            lev1(S,C,Customers,one);
        }
        else if(y==3){
            System.out.println();
            System.out.print("Enter Product ID::");
            double id=S.nextDouble();
            System.out.print("Enter Quantity::");
            int q=S.nextInt();
            C.add_product_to_cart(id,q,one);
            lev1(S,C,Customers,one);
        }
        else if(y==4){
            System.out.println();
            int i=1;
            int f=0;
            for(Deal D: one.Deals){
                int quantity=Math.min(D.P1.Quantity,D.P2.Quantity);
                if(quantity>0) {
                    f=1;
                    System.out.println("Deal - " + i);
                    System.out.println("\tProduct 1::" + D.P1.P_Name);
                    System.out.println("\tProduct 1::" + D.P2.P_Name);
                    if (C.ELITE) {
                        System.out.println("\tDeal Price ::" + D.price_E);
                    } else if (C.PRIME) {
                        System.out.println("\tDeal Price ::" + D.price_P);
                    } else if (C.NORMAL) {
                        System.out.println("\tDeal Price ::" + D.price_N);
                    }
                    if (D.P1.Quantity > D.P2.Quantity) {
                        System.out.println("\tQuantity ::" + D.P2.Quantity);
                    } else {
                        System.out.println("\tQuantity ::" + D.P1.Quantity);
                    }
                    i++;
                    System.out.println();
                }
            }
            if(f==1) {
                System.out.print("Enter Deal number ::");
                int dn = S.nextInt();
                System.out.print("Enter Quantity ::");
                int q = S.nextInt();
                System.out.println();
                C.add_deal_to_cart(dn, q, one);
            }
            else{
                System.out.println("Dear "+C.Name+", there are no deals for now");
                System.out.println("Please come again later for deals");
                System.out.println();
            }
            lev1(S,C,Customers,one);
        }
        else if(y==5){
            System.out.println();
            C.view_coupon();
            lev1(S,C,Customers,one);
        }
        else if(y==6){
            System.out.println();
            C.account_balance();
            lev1(S,C,Customers,one);
        }
        else if(y==7){
            System.out.println();
            C.view_cart();
            lev1(S,C,Customers,one);
        }
        else if(y==8){
            System.out.println();
            C.empty_cart();
            lev1(S,C,Customers,one);
        }
        else if(y==9){
            System.out.println();
            C.checkout(one);
            lev1(S,C,Customers,one);
        }
        else if(y==10){
            System.out.println();
            if(C.NORMAL){System.out.println("Current Status: NORMAL");}
            if(C.PRIME){System.out.println("Current Status: PRIME");}
            if(C.ELITE){System.out.println("Current Status: ELITE");}
            System.out.print("Enter new Status:: ");
            String status=S.next();
            C.upgrade_status(status.toUpperCase(),C);
            lev1(S,C,Customers,one);
        }
        else if(y==11){
            System.out.println();
            System.out.print("Enter amount to be added :: ");
            double amount=S.nextDouble();
            C.add_amount(amount);
            lev1(S,C,Customers,one);
        }
        else if(y==12){
            System.out.println("Bye Bye "+C.Name);
            MainEntry(S,Customers,one);
        }
        else{
            System.out.println();
            System.out.println("Invalid Input");
            lev1(S,C,Customers,one);
        }
    }

    public static void main(String[] args) {
        Scanner S=new Scanner(System.in);
        Admin one =new Admin("Beff Jezos","*****");
        Vector<Customer> Customers=new Vector<>();
        MainAdmin(S,Customers,one);
    }
}