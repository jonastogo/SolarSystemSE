import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneBuild extends Application {

	private static final double	WindowSizeX	= 1920;
	private static final double	WindowSizeY	= 1080;
	Planet						sun;
	private static final String	sunTexture	= "file:Data/2kSonne.jpg";
	ArrayList<Planet>			allPlanets	= new ArrayList<Planet>();
	private Stack<Map>			stack		= new Stack<Map>();
	private Rotate				rotateX, rotateY, rotateZ;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// set Stage boundaries to visible bounds of the main screen
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		double x = primaryScreenBounds.getWidth();
		double y = primaryScreenBounds.getHeight();
		GridPane g1 = new GridPane();
		Scene scene = new Scene(g1, x, y, true, SceneAntialiasing.BALANCED);
		// g1.setStyle("-fx-background-image: url(" + "\"Data/2kSterne.jpg\"" + "); " + "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
		sun = new Planet(150, 0, 0, 0, sunTexture);
		sun.spawnPlanets(8);
		allPlanets.add(sun);
		allPlanets.addAll(Arrays.asList(sun.planets));
		for (Planet p : allPlanets) {
			g1.getChildren().add(p.getSphere());
		}
		scene.setFill(Color.rgb(0, 0, 0));
		PerspectiveCamera camera = new PerspectiveCamera();
		scene.setCamera(camera);
		camera.setTranslateY(camera.getTranslateY() - 540 + sun.radius);
		camera.setTranslateX(camera.getTranslateX() - 960 + sun.radius);
		camera.setTranslateZ(camera.getTranslateZ() - 5000);
		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(false);

		Timeline tl = new Timeline();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
					case A:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(45, Rotate.Z_AXIS));
						stack.push(new Map("z", (byte) 45));
						break;
					case D:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(-1, Rotate.Z_AXIS));
						stack.push(new Map("z", (byte) (-1)));
						break;
					case W:
						camera.getTransforms().addAll(rotateX = new Rotate(1, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						stack.push(new Map("x", (byte) 1));
						break;
					case S:
						camera.getTransforms().addAll(rotateX = new Rotate(-1, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						stack.push(new Map("x", (byte) (-1)));
						break;
					case Q:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(1, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						stack.push(new Map("y", (byte) 1));
						break;
					case E:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(-1, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						stack.push(new Map("y", (byte) (-1)));
						break;
					case NUMPAD8:
					case I:
						camera.setTranslateY(camera.getTranslateY() + 100);
						break;
					case NUMPAD2:
					case K:
						camera.setTranslateY(camera.getTranslateY() - 100);
						break;
					case NUMPAD4:
					case J:
						camera.setTranslateX(camera.getTranslateX() - 100);
						break;
					case NUMPAD6:
					case L:
						camera.setTranslateX(camera.getTranslateX() + 100);
						break;
					case NUMPAD9:
					case U:
						camera.setTranslateZ(camera.getTranslateZ() + 100);
						break;
					case NUMPAD1:
					case O:
						camera.setTranslateZ(camera.getTranslateZ() - 100);
						break;
					case NUMPAD0:
					case P:
						if (tl.getStatus() == Animation.Status.RUNNING)
							tl.pause();
						else
							tl.play();
						break;
					case DIGIT1:
						if (g1.getChildren().get(1).getOpacity() == 1)
							g1.getChildren().get(1).setOpacity(0);
						else
							g1.getChildren().get(1).setOpacity(1);
						break;
					case DIGIT2:
						if (g1.getChildren().get(2).getOpacity() == 1)
							g1.getChildren().get(2).setOpacity(0);
						else
							g1.getChildren().get(2).setOpacity(1);
						break;
					case DIGIT3:
						if (g1.getChildren().get(3).getOpacity() == 1)
							g1.getChildren().get(3).setOpacity(0);
						else
							g1.getChildren().get(3).setOpacity(1);
						break;
					case DIGIT4:
						if (g1.getChildren().get(4).getOpacity() == 1)
							g1.getChildren().get(4).setOpacity(0);
						else
							g1.getChildren().get(4).setOpacity(1);
						break;
					case DIGIT5:
						if (g1.getChildren().get(5).getOpacity() == 1)
							g1.getChildren().get(5).setOpacity(0);
						else
							g1.getChildren().get(5).setOpacity(1);
						break;
					case DIGIT6:
						if (g1.getChildren().get(6).getOpacity() == 1)
							g1.getChildren().get(6).setOpacity(0);
						else
							g1.getChildren().get(6).setOpacity(1);
						break;
					case DIGIT7:
						if (g1.getChildren().get(7).getOpacity() == 1)
							g1.getChildren().get(7).setOpacity(0);
						else
							g1.getChildren().get(7).setOpacity(1);
						break;
					case DIGIT8:
						if (g1.getChildren().get(8).getOpacity() == 1)
							g1.getChildren().get(8).setOpacity(0);
						else
							g1.getChildren().get(8).setOpacity(1);
						break;
					case DIGIT9:
						if (g1.getChildren().get(9).getOpacity() == 1)
							g1.getChildren().get(9).setOpacity(0);
						else
							g1.getChildren().get(9).setOpacity(1);
						break;
					case DIGIT0:
						if (g1.getChildren().get(0).getOpacity() == 1)
							g1.getChildren().get(0).setOpacity(0);
						else
							g1.getChildren().get(0).setOpacity(1);
						break;
					case F11:
						if (!stage.isFullScreen())
							stage.setFullScreen(true);
						else
							stage.setFullScreen(false);
						break;
					case ESCAPE:
						System.exit(1);
						break;
					case C:
						camera.setTranslateX(360.0);
						camera.setTranslateY(-8200.0);
						camera.setTranslateZ(-22600.0);
						for (int i = 0; i < 290; i++) {
							camera.getTransforms().addAll(rotateX = new Rotate(-1, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
							stack.push(new Map("x", (byte) (-1)));
						}
						break;
					case R:
						System.out.println(camera.getTranslateX() + " / " + camera.getTranslateY() + " / " + camera.getTranslateZ());
						camera.setTranslateY(camera.getTranslateY() - 540 + sun.radius);
						camera.setTranslateX(camera.getTranslateX() - 960 + sun.radius);
						camera.setTranslateZ(camera.getTranslateZ() - 5000);
						while (!stack.empty()) {
							switch (stack.peek().getAxis()) {
								case "x":
									Map x = stack.pop();
									camera.getTransforms().addAll(rotateX = new Rotate((x.getValue() * (-1)), Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
									break;
								case "y":
									Map y = stack.pop();
									camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate((y.getValue() * (-1)), Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
									break;
								case "z":
									Map z = stack.pop();
									camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate((z.getValue() * (-1)), Rotate.Z_AXIS));
									break;
								default:
									System.out.println("keine gültige Map!");
									break;
							}
						}
						break;
					default:
						System.out.println("Sie haben den useless Knopf " + keyEvent.getCode() + " gedrückt!");
						break;
				}
			}
		});

		tl.setCycleCount(Animation.INDEFINITE);
		KeyFrame moveEarth = new KeyFrame(Duration.seconds(.0200), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				for (int i = 1; i < g1.getChildren().size(); i++) {
					Planet p = allPlanets.get(i);
					p.setShowCoords();
					PVector v = p.getShowCoords();
					g1.getChildren().get(i).setTranslateX(v.x + sun.radius - p.radius);
					g1.getChildren().get(i).setTranslateY(v.y);
					g1.getChildren().get(i).setTranslateZ(v.z);
					p.orbit();
				}
			}
		});

		tl.getKeyFrames().add(moveEarth);
		tl.play();

		for (Node n : g1.getChildren())
			rotateAroundYAxis(n, 4).play();
	}

	private RotateTransition rotateAroundYAxis(Node node, double s) {
		RotateTransition rotate = new RotateTransition(Duration.seconds(s), node);
		rotate.setAxis(Rotate.Y_AXIS);
		rotate.setFromAngle(360);
		rotate.setToAngle(0);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setCycleCount(RotateTransition.INDEFINITE);

		return rotate;
	}
}
