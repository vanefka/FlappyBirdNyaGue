package com.ivanzkyanto.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ivanzkyanto.flappybird.MyGame;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private TextureRegion backgroundRegion;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png"); // Resource sementara
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        backgroundRegion = new TextureRegion(background,0,0, MyGame.WIDTH, background.getHeight());
        playBtn = new Texture("playbtn.png"); // Ini juga
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundRegion, 0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        sb.draw(playBtn, (MyGame.WIDTH/2) - (playBtn.getWidth()/2), (MyGame.HEIGHT/2) - playBtn.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu state disposed");
    }
}
