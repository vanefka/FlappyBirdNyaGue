package com.ivanzkyanto.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.ivanzkyanto.flappybird.MyGame;
import com.ivanzkyanto.flappybird.sprites.Bird;
import com.ivanzkyanto.flappybird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        bird = new Bird(40, 200);
        cam.setToOrtho(false, MyGame.WIDTH / 2, MyGame.HEIGHT / 2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt, cam.viewportHeight);
        cam.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);

        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosBotTube().y);
            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
        }
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();

//        debug();
    }

    @Override
    public void dispose() {

    }

//    private void debug() {
//        System.out.println("camPositionX : " + cam.position.x);
//    }
}
