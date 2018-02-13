package show25;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShowJPG extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private static final String[] httpArray = {
            "https://www.flyopedia.com/blog/wp-content/uploads/2016/03/1280px-Mind_the_Gap_Royal_Gorge_Bridge.jpg",
            "https://fakty.ictv.ua/wp-content/uploads/2016/12/29/1932524.jpg",
            "https://fakty.ictv.ua/wp-content/uploads/2016/12/29/1932525.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085048_021.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085005_027.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085033_029.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085046_034_3.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085003_037.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085062_043.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085024_044.jpg",

            "http://7-themes.com/data_images/out/51/6945254-beautiful-landscape-photos.jpg",
            "http://7-themes.com/7037846-cool-back-grounds_1381.html",
            "http://7-themes.com/6935180-cool-back-grounds.html",
            "https://media.cntraveler.com/photos/58d2b8cfbf12f06bcf42e607/master/w_1440,c_limit/arashiyama-forest-kyoto-japan-GettyImages-528314677.jpg",
            "https://media.cntraveler.com/photos/58d2c0a49033b024213c8e7c/master/w_1440,c_limit/miyajima-torii-gate-GettyImages-460861177.jpg",
            "https://media.cntraveler.com/photos/58d2c0a97e623821b9f3181d/master/w_1440,c_limit/yoshinoyama--japan-GettyImages-488852217.jpg",
            "https://media.cntraveler.com/photos/58d358d41021b64be09794a3/master/w_1440,c_limit/fushimi-inari-taisha-kyoto-GettyImages-504849486.jpg",
            "https://media.cntraveler.com/photos/58d2b8cb7e623821b9f31819/master/w_1440,c_limit/nara-park-deer-GettyImages-485184643.jpg",
            "http://2.bp.blogspot.com/-RoQQ5fACBTI/U1t3hEO8w3I/AAAAAAAAL4k/4gSg7FiYGqg/s1600/beautiful-images-wallpapers-(1).jpg",
            "https://static.pexels.com/photos/417142/pexels-photo-417142.jpeg",

            "https://static.pexels.com/photos/9291/nature-bird-flying-red.jpg",
            "https://static.pexels.com/photos/39629/tiger-tiger-baby-tigerfamile-young-39629.jpeg",
            "https://images.pexels.com/photos/133408/pexels-photo-133408.jpeg",
            "https://images.pexels.com/photos/146080/pexels-photo-146080.jpeg",
            "https://res.cloudinary.com/twenty20/private_images/t_watermark-criss-cross-10/v1443527203000/photosp/18824cdd-1298-47cc-ab40-c862961c25fa/stock-photo-nature-sea-beach-sand-landscape-love-summer-ocean-california-18824cdd-1298-47cc-ab40-c862961c25fa.jpg",
            "https://i.pinimg.com/236x/e0/34/9a/e0349a497aaea2d6a4e629164f2d388c.jpg",
            "http://i.imgur.com/RUQkp.jpg",
            "https://drscdn.500px.org/photo/49095066/m%3D900/bf38713db43d332656fb358d95a5876d",
            "https://drscdn.500px.org/photo/109491785/m%3D900/v2?webp=true&sig=51dc2a634816e224b19602961b3209f6fc12abd18aa6658f9b18918b5715d486",
            "https://drscdn.500px.org/photo/59639160/m%3D900/v2?webp=true&sig=e0dbbc0e63c3b8f4299de5b2126b1fba1ac9293198bed8c7257dcd845fe9d90e",

            "https://drscdn.500px.org/photo/98058099/m%3D900/v2?webp=true&sig=35242d8ada599c4df71118634b0544021c6f9cb229e35b9779239da5ab3a959f",
            "https://drscdn.500px.org/photo/67091465/m%3D900/v2?webp=true&sig=9ac73636fb819a120c5d1b440018619bce2155e44b3c7d76cccf6db66e458a5a",
            "https://drscdn.500px.org/photo/98577193/m%3D900/v2?webp=true&sig=40b7044ce851959b2584133eb3b293c4c1216f2e870b9e5a5eba1ac876878b82",
            "https://drscdn.500px.org/photo/97551023/m%3D900/v2?webp=true&sig=9ccf7ca4d25987a16eef6283b34b6c220ef7f03cf5967432944d5dbe32725896",
            "https://drscdn.500px.org/photo/110153683/m%3D900/v2?webp=true&sig=892a4e63eebeed2bc7ac9d3f6d8520448286750ef80ddbda810465012e685cb2",
            "https://drscdn.500px.org/photo/74152219/m%3D900/v2?webp=true&sig=413b480fea17536e37f532f12ad1dfe226cf8cdcff87d565e8c157785c4dd2a6",
            "https://drscdn.500px.org/photo/127077157/m%3D900/v2?webp=true&sig=ac4ed1b71298e9dfcbaab7a0b23c2fe1a777ac5ef2b2f6c2431c3ca5887dea17",
            "https://drscdn.500px.org/photo/89889713/m%3D900/v2?webp=true&sig=820188e44d75f64e8630278d51e05a5fe258e9cbc4f2e92d3eeb839d91cc0c62",
            "https://drscdn.500px.org/photo/99722037/m%3D900/v2?webp=true&sig=2586c591a8c95d1af9f1d1ba74d566b266e520b85a029b99243de6899b4c7af2",
            "https://drscdn.500px.org/photo/93076423/m%3D900/v2?webp=true&sig=7719d4592b5800a7c722abb9be10f8c062f53e4fdf086b1d6f031c8d38a3caf2",
            "https://drscdn.500px.org/photo/107649989/m%3D900/v2?webp=true&sig=537297da7d0a3b15a77c9afe592f46693e087c843cb2103c7ca04ba92e91e067",

            "https://drscdn.500px.org/photo/89103973/m%3D900/v2?webp=true&sig=1775a5a2f78c9e78c1ee068d6b62d893992a6f48eae90ee43ae56c94b79b8ccb",
            "http://picpulp.com/wp-content/uploads/2013/04/3d-natur-sch-ne-tapete2560x160060189-1024x640.jpg",
            "http://picpulp.com/wp-content/uploads/2013/04/Beach-Wallpaper.jpg",
            "http://picpulp.com/wp-content/uploads/2013/04/beautiful-nature-wallpapers-18-b-o-ibackgroundz.com_-1024x640.jpg",
            "http://picpulp.com/wp-content/uploads/2013/04/Color_Born_HD_Wallpaper_by_3D_Xtrinity-1024x640.jpg",
            "http://kaifolog.ru/uploads/posts/2016-01/1454085065_057.jpg",
            "https://politeka.net/wp-content/uploads/2017/12/lomachenko-8.jpg",
            "http://wisetoast.com/wp-content/uploads/2015/10/Charlize-Theron-most-beautiful-woman.jpg",
            "http://7-themes.com/data_images/collection/6/4476130-beautiful-photos.jpg",
            "http://7-themes.com/data_images/collection/6/4476272-beautiful-photos.jpg",
    };

    private static final double width = 1100;
    private static final double height = 700;

    private static final String[] IMAGE_URLS = new String[] {
            "https://media.istockphoto.com/photos/two-red-balloons-picture-id505640210?k=6&m=505640210&s=612x612&w=0&h=cr76FHFVLlU1y-artTN3-T77P1XzTwbfou2zjLlZxms=",
            "http://i.telegraph.co.uk/multimedia/archive/03597/POTD_chick_3597497k.jpg",
            "http://i.telegraph.co.uk/multimedia/archive/03519/potd-squirrel_3519920k.jpg",
            "https://media.gettyimages.com/photos/colorful-powder-explosion-picture-id550582551"
    };

    @Override
    public void start(Stage primaryStage) throws Exception {

        ExecutorService service = Executors.newFixedThreadPool(10);

        Pane pane = new Pane();
        Button reload = new Button("Reload");
        reload.setTranslateX(width-35);
        reload.setTranslateY(10);
        pane.getChildren().add(reload);

        Random random = new Random();

        reload.setOnMouseClicked((event)-> {
            for (int i = 0; i < 25; i++) {
                String imageURL = httpArray[random.nextInt(httpArray.length)];
                final int index = i;
                service.submit(() -> {
                    Image image = new Image(imageURL);
                    ImageView imageView = new ImageView(image);
                    Platform.runLater(() -> {
                        imageView.setTranslateX((((index+1) % 5) * 205));
                        imageView.setTranslateY(138 * (index / 5));
                        imageView.setFitWidth(200);
                        imageView.setPreserveRatio(true);
                        pane.getChildren().addAll(imageView);
                    });
                });
            }
        });

        setParameter(primaryStage);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((event)->service.shutdown());
    }

    private void setParameter(Stage stage){
        stage.setMinHeight(height);
        stage.setMinWidth(width);
        stage.setMaxHeight(height);
        stage.setMaxWidth(width);
    }
}
