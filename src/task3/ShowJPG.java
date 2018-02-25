package task3;

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
            "https://www.flyopedia.com/blog/wp-content/uploads/2016/03/1280px-Mind_the_Gap_Royal_Gorge_Bridge.jpg", //0
            "http://kaifolog.ru/uploads/posts/2016-01/1454085005_027.jpg",//4
            "http://kaifolog.ru/uploads/posts/2016-01/1454085033_029.jpg",//5
            "http://kaifolog.ru/uploads/posts/2016-01/1454085046_034_3.jpg",//6
            "http://kaifolog.ru/uploads/posts/2016-01/1454085003_037.jpg",//7
            "http://kaifolog.ru/uploads/posts/2016-01/1454085062_043.jpg",//8
            "http://kaifolog.ru/uploads/posts/2016-01/1454085024_044.jpg",//9
            "https://media.cntraveler.com/photos/58d2b8cfbf12f06bcf42e607/master/w_1440,c_limit/arashiyama-forest-kyoto-japan-GettyImages-528314677.jpg",//13
            "https://media.cntraveler.com/photos/58d2c0a49033b024213c8e7c/master/w_1440,c_limit/miyajima-torii-gate-GettyImages-460861177.jpg",//14
            "https://media.cntraveler.com/photos/58d2c0a97e623821b9f3181d/master/w_1440,c_limit/yoshinoyama--japan-GettyImages-488852217.jpg",//15
            "https://media.cntraveler.com/photos/58d358d41021b64be09794a3/master/w_1440,c_limit/fushimi-inari-taisha-kyoto-GettyImages-504849486.jpg",//16
            "https://media.cntraveler.com/photos/58d2b8cb7e623821b9f31819/master/w_1440,c_limit/nara-park-deer-GettyImages-485184643.jpg",//17
            "http://2.bp.blogspot.com/-RoQQ5fACBTI/U1t3hEO8w3I/AAAAAAAAL4k/4gSg7FiYGqg/s1600/beautiful-images-wallpapers-(1).jpg",//18
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
            "http://kaifolog.ru/uploads/posts/2016-01/1454085065_057.jpg",
            "http://wisetoast.com/wp-content/uploads/2015/10/Charlize-Theron-most-beautiful-woman.jpg",
            "https://media.istockphoto.com/photos/two-red-balloons-picture-id505640210?k=6&m=505640210&s=612x612&w=0&h=cr76FHFVLlU1y-artTN3-T77P1XzTwbfou2zjLlZxms=",
            "http://i.telegraph.co.uk/multimedia/archive/03597/POTD_chick_3597497k.jpg",
            "http://i.telegraph.co.uk/multimedia/archive/03519/potd-squirrel_3519920k.jpg",
            "https://media.gettyimages.com/photos/colorful-powder-explosion-picture-id550582551",
            "http://parasola.ru/wp-content/uploads/img/img1.liveinternet.ru/images/attach/c/6/91/537/91537691_beautiful_women.jpg",
            "https://files.adme.ru/files/news/part_153/1533065/1350765-7c6ae5269754675635e9ee71eef-1498451885-650-230b8825b5-1498654116.jpg",
            "https://i.ytimg.com/vi/bMsOg15WUac/maxresdefault.jpg",
            "http://v.img.com.ua/b/600x500/3/c2/2bb68bf4e51d8272737a10a7774bfc23.jpg",
            "https://i.pinimg.com/736x/6e/ce/b9/6eceb9a2ed7cad479881853715893666--crossfit-athletes-crossfit-women.jpg",
            "https://s2.reutersmedia.net/resources/r/?m=02&d=20171206&t=2&i=1212560725&r=LYNXMPEDB506F&w=1280",
            "https://www.ironmind-store.com/images/PS-PDN1_f.jpg",
            "https://media.gettyimages.com/photos/athens-greece-greeces-pyrros-dimas-looks-to-the-spectators-as-he-picture-id51201320?s=612x612",
            "http://ridna.ua/wp-content/uploads/2012/04/fanaty.jpeg",
            "https://s0.rbk.ru/v6_top_pics/resized/945xH/media/img/7/94/754666907504947.jpg",
            "http://dynamo.kiev.ua/media/posts/2016/02/24/unt16.jpg",
            "https://img.hurtom.com/i/2016/01/92487dd01d85.md.jpg",
            "https://i.ytimg.com/vi/TAa_GtLzYeI/maxresdefault.jpg"
    };

    private static final double width = 1100;
    private static final double height = 700;

    @Override
    public void start(Stage primaryStage) throws Exception {

        final double imageWidth = 200;
        final double plusWidth = 205;
        final double imageHeight = 135;
        final double plusHeight = 138;
//        ImageView[] images = new ImageView[25];

        ExecutorService service = Executors.newFixedThreadPool(10);

        Pane pane = new Pane();
        Button reload = new Button("Reload");
        reload.setTranslateX(width-75);
        reload.setTranslateY(10);
        pane.getChildren().add(reload);

        reload.setOnMouseClicked((event)-> {
            if (pane.getChildren().size() > 1) pane.getChildren().remove(1, pane.getChildren().size());
            Shuffle(httpArray);
            for (int i = 0; i < 25; i++) {
                String imageURL = httpArray[i];
                final int index = i;
                service.submit(() -> {
                    Image image = new Image(imageURL);
                    ImageView imageView = new ImageView(image);
                    Platform.runLater(() -> {
                        imageView.setFitWidth(imageWidth);
                        imageView.setFitHeight(imageHeight);
                        double slideX = (imageWidth - imageHeight * (image.getWidth()/image.getHeight()));
                        slideX = slideX < 0 ? 0 : slideX/2;
                        double slideY = (imageHeight - imageWidth * (image.getHeight()/image.getWidth()));
                        slideY = slideY < 0 ? 0 : slideY/2;
                        imageView.setTranslateX(((index) % 5) * plusWidth + slideX);
                        imageView.setTranslateY(plusHeight * (index / 5) + slideY);
                        imageView.setPreserveRatio(true);
//                        images[index] = imageView;
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

    private static void Shuffle(String[] array)
    {
        Random rng = new Random();
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int a = rng.nextInt(n);
            String s = array[i];
            array[i] = array[a];
            array[a] = s;
        }
    }

    private void setParameter(Stage stage){
        stage.setMinHeight(height);
        stage.setMinWidth(width);
        stage.setMaxHeight(height);
        stage.setMaxWidth(width);
    }
}
