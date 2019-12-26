         Polygon polygon = new Polygon();
         Polygon polygon2 = new Polygon();
         Polygon polygon3 = new Polygon();
         VBox vbox = new VBox();
         Rectangle rectangle = new Rectangle(100.0,150.0);
         rectangle.setFill(Color.WHITE);
         rectangle.setStroke(Color.BLACK);
         rectangle.setArcHeight(15);
         rectangle.setArcWidth(15);
         polygon.getPoints().addAll(new Double[]{ 
         60.0, 10.0, 
         90.0, 30.0, 
         60.0, 50.0, 
         30.0, 30.0, });
         polygon2.getPoints().addAll(new Double[]{ 
         60.0, 10.0, 
         90.0, 30.0, 
         60.0, 50.0, 
         30.0, 30.0, }); 
         polygon3.getPoints().addAll(new Double[]{ 
         60.0, 10.0, 
         90.0, 30.0, 
         60.0, 50.0, 
         30.0, 30.0, }); 
         polygon.setFill(Color.RED);
         polygon2.setFill(Color.BLUE);
         vbox.getChildren().addAll(polygon,polygon2,polygon3);
         vbox.setAlignment(Pos.CENTER);
