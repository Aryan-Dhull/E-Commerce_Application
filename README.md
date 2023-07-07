# E-Commerce_Application

The Great Indian Festival Season has begun and young entrepreneurs of our institute have just started their startup, “FLIPZON”. It’s a product based start-up where people can buy a plethora of products and get them delivered at home. “FLIPZON” founders Beff Jezos and Gill Bates want this product to be revolutionary and want you to build an application for the same.

The application needs to serve the following flow:

    1) The application will have a command line based user interface where you can opt to enter as the admin of the application or as a customer.
    
    2) The customers have been divided into three categories: “ELITE”, “PRIME”, “NORMAL”. The difference between these 3 categories is on the basis of the discounts/perks they get. The details of the three       categories will be explained separately below.
    
    3) You as the admin of the application will add some product categories under which you need to add some products that the company wants to sell. (at least 3 products for each category, and a minimum of 3 categories is expected).
    
    4) We can now enter as users of the application. When you enter the application, the user can see all the products and explore them but one cannot add it to the cart and buy it when the user has not registered.
    
    5) Once the user has registered on the application portal, the user becomes a “NORMAL” customer for the user by default. A user by default has Rs 1000 in its account. You can maintain a wallet attribute for each user (or customer) which stores the balance.
    
    6) The user can buy a membership and purchase monthly/yearly subscriptions to upgrade to a “ELITE” / ”PRIME” customer. An “ELITE” membership costs Rs 300 and a “PRIME” membership costs Rs 200. Both the costs are for one month membership.
    
    7) To buy a membership you should redirect to payment and deduct that amount from the balance of the customer.
    
    8) Customers also get one time use coupons which they can apply and get a discount on the total amount to be paid. The details of coupons will be explained further.
    
    9) The Customer can go through the list of products and choose items to add in the cart. Once all items are added in the cart, the customer can choose to delete items from the cart or checkout from the cart to pay the total amount. Once paid, the total amount will be deducted from the account of the user.
    
    10) Please note that if the customer has insufficient amount in the account, decline the payment saying that there is not sufficient balance in your account.
    
    11) You need to return to the home page after payment.
    
    12) Once you are done shopping, you can log out from the account and sign in as another user.

NOTE:- each product will have a unique ID. You should take care of this while implementing




Let’s see some of the functionalities of some of the entities that are important in this application.


Admin Functionalities:

    1) Enter as Admin: In this functionality, the application should take in your username and roll no. as password and if they’re correct, then you must get all privileges of an admin.

    2) Add Category: This functionality will help the admin to add a new category. It’ll take the category ID and the name of the category as input. Also take in input a product name and its details because there can not be an empty category.

    3) Delete Category: This functionality will help the admin to delete a new category. It’ll take the name of the category and category ID as input. Once the input is taken the whole category (including the details of the products inside it) will be deleted.

    4) Add Product: If you want to add a new product take in input the category ID and add the name of the product and its details (such as price, quantity) and it should get added in the respective category. (NOTE: the category should exist otherwise add a category first.)

    5) Delete Product: If you want to delete a product take in input the name of the category and product ID. The product from that category should get deleted.

    6) Set Discount on Product: this functionality will allow the admin to set the discount to be given on a particular product to specific customers. It will take in input the product ID , the discount percentage to be set and the customer categories which can avail this offer. Discount rates differ for each category of customer.

    7) Add giveaway deals: this functionality allows the admin to give in the product ID of 2 products and give a lower price than their combined prices as a giveaway deal. (you can maintain an ID for deals also to make it convenient to track)

(NOTE:- you shouldn’t keep a category empty, if there is no product in a category, you should ask to add a product or else the category would get deleted).



Customer functionalities:

    1) SignUp : This functionality will help the customer to first register and make an account on the application. It will take the name, age, phone number, email id and password as input.

    2) Log in: This functionality will allow the already registered customer to log in by providing the name, email id and password as input. A customer will be able to login only if the fields match with the ones the database.

    3) Upgrade status: this allows a customer to upgrade to an elite customer or a prime customer.

    4) Explore Product Catalog: This functionality allows the customers to surf through the product categories and products under the categories.

    5) Add product to cart: user specifies a product ID and the quantity which is added to cart, depending on the availability of the product.

    6) Make Payment: This functionality will allow the customer to make payment and shows the total bill of all items in cart along with the list of coupons one can apply.
    Below are the privileges that “ELITE” and “PRIME” customers enjoy:

          1) The “ELITE” customers will be eligible for all the discounts/coupons set by the admin. They will enjoy a 10 % discount on each product they buy. Also they can get a free surprise from “FLIPZON”. This means that the “ELITE” customers can randomly get a free product as a surprise on making an order. All deliveries will be made within 2 days for all the “ELITE” customers. They have to pay delivery charges equal to Rs 100 flat on all their orders. Also, “ELITE” customers receive 3-4 coupons for every order above the limit of Rs 5000

          2) For “PRIME” they will also be eligible for the discounts/coupons set by the admin. They will enjoy a 5% discount on each product they buy. All deliveries will be made within 3-6 days (this can be randomized in the implementation) and they have to pay delivery charges equal to flat Rs 100 + 2% of the order value. They receive 1-2 coupons on orders above Rs 5000.

    Note: “NORMAL” customers do not receive any discount, deliveries are made within 7-10 days for them and have to pay delivery charges equal to 100 + 5% of order value. They receive no coupons whatsoever.
    Note: Every coupon can range from anywhere between 5% - 15% discount, which can be randomly generated. Only one coupon per order can be applied and the coupon with the highest discount will be applied automatically.
    
    Note: always apply product-wise discount. Out of all available discounts (from coupons, based on user category, individual product discount, pick the maximum one for each product) There will also be a feature by FLIPZON for attracting customers by providing giveaway deals that are special deals which customers can enjoy. These deals will be set by the admin by combining 2 products and offering them at a lower combined price than the price after applying product specific discount on both the products. Note that no other discounts are to be applied on any deal for any user type. Each deal will have three prices associated with it corresponding to each product category.



For demonstration of the application, you need to show at least 3 categories of products and customers of each category. The Application should allow the following options:

    1) Exit the application: this will terminate the entire application.
    
    2) You can enter as admin or enter as a customer.
    
    3) You should allow a visiting user who has not registered just to explore the application but not allow the user to buy anything.
    
    4) The application should also have an option to show the available special deals to the users (the ones where 2 products are sold in combination).
