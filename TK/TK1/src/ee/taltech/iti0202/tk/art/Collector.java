package ee.taltech.iti0202.tk.art;

import java.util.ArrayList;
import java.util.List;

public class Collector {
    List<Painting> paintingList = new ArrayList<>();

//    /**
//     *
//     */
//    public Collector() {
//    }

    /**
     * @param painting painting
     * @return false
     */
    public boolean addPainting(Painting painting) {
        if (!paintingList.contains(painting)) {
            if (paintingList.size() > 0) {
                for (int i = 0; i < paintingList.size(); i++) {
                    if (paintingList.get(i).getTitle().equals(painting.getTitle())) {
                        return false;
                    } else {
                        paintingList.add(painting);
                        return true;
                    }
                }
            }
            paintingList.add(painting);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param painting        painting
     * @param fellowCollector fellowCollector
     * @return false
     */
    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (paintingList.contains(painting) && fellowCollector != this) {
            if (fellowCollector.addPainting(painting)) {
                paintingList.remove(painting);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @return List
     */
    public List<Painting> getPaintings() {
        return paintingList;
    }

//    public static void main(String[] args) {
//
//        Painting painting1 = new Painting("The last supper", "Leonardo");
//        System.out.println(painting1);  // This is a painting named The last supper and made by Leonardo.
//        Painting painting2 = new Painting("Woman with Red Head Scarf");
//        System.out.println(painting2);  // This is a painting named Woman with Red Head Scarf
//        and made by an unknown artist.
//
//        Painting forgery = new Painting("The last supper", "Leonerdo");
//        System.out.println(forgery);  // This is a painting named The last supper and made by Leonerdo.
//        System.out.println();
//
//        Collector collector1 = new Collector();
//        Collector collector2 = new Collector();
//
//        System.out.println(collector1.addPainting(painting1)); // true
//        System.out.println(collector1.addPainting(painting1)); // false
//        System.out.println(collector1.addPainting(painting2)); // true
//        System.out.println();
//        System.out.println(collector2.getPaintings()); // []
//        System.out.println(collector1.getPaintings());
//// [This is a painting named The last supper and made by Leonardo.,
//// This is a painting named Woman with Red Head Scarf and made by an unknown artist.]
//        System.out.println();
//        System.out.println(collector1.sellPainting(painting1, collector2)); // true
//        System.out.println(collector2.sellPainting(painting2, collector1)); // false
//        System.out.println(collector1.sellPainting(painting2, collector1)); // false
//        System.out.println();
//        System.out.println(collector1.getPaintings());
//// [This is a painting named Woman with Red Head Scarf and made by an unknown artist.]
//        System.out.println(collector2.getPaintings());
//// [This is a painting named The last supper and made by Leonardo.]
//        System.out.println(collector2.addPainting(forgery)); // false
//
//    }
}
