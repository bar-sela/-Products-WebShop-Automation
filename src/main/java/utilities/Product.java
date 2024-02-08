    package utilities;

    import java.util.List;

    public class Product {
        String name ;
        double price;

        public Product(String name, double price){
            this.name = name;
            this.price = price;
        }
        /// get & set
        public double getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public static boolean isSortedByPrice(List<Product> productList) {
            for (int i = 1; i < productList.size(); i++) {
                if (productList.get(i - 1).getPrice() > productList.get(i).getPrice()) {
                    return false;
                }
            }
            return true;
        }
        public static boolean isSortedByName(List<Product> productList) {
            for (int i = 1; i < productList.size(); i++) {
                if (productList.get(i - 1).getName().compareTo(productList.get(i).getName()) > 0) {
                    return false;
                }
            }
            return true;
        }
    }