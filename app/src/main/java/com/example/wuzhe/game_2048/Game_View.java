package com.example.wuzhe.game_2048;
import com.example.wuzhe.game_2048.MainActivity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-9-27.
 */
public class Game_View extends GridLayout {

    public Game_View(Context context) {
        super(context);
        initGameView();
    }

    public Game_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public Game_View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    private void initGameView(){

        setColumnCount(4);
        setBackgroundColor(0xffbbadaa);
        setOnTouchListener(new OnTouchListener() {

           private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = motionEvent.getX() - startX;
                        offsetY = motionEvent.getY() - startY;

                        if(Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                //Context context ;
                                //Toast.makeText(context,"left", Toast.LENGTH_SHORT).show();
                                System.out.println("left");
                                swipeLeft();
                            }
                            else if (offsetX > 5) {
                                System.out.println("right");
                                swipeRight();
                            }
                        }
                        else
                        {
                            if (offsetY < -5) {
                                //Context context ;
                                //Toast.makeText(context,"left", Toast.LENGTH_SHORT).show();
                                System.out.println("UP");
                                swipeUp();
                            }
                            else if (offsetY > 5) {
                                System.out.println("Down");
                                swipeDown();
                            }
                        }

                        break;
                    default:
                        break;
                }

                return true;
            }
        });
        addRandomNum();
    }

    private void swipeUp(){
        for(int y = 0 ; y < 4; y++ ){
            for(int x = 0  ; x < 4 ; x++){
                for (int x1 = x + 1;x1 < 4 ; x1++){
                    if(cardsMap[x1][y].getNum() != 0){
                        if(cardsMap[x][y].getNum() == 0){
                            System.out.println("x1y不为0，xy处为0 "+"x ="+x + "   y="+y);
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum() );
                            cardsMap[x1][y].setNum(0);
                            x--;
                            break;
                        }
                        else if(cardsMap[x][y].getNum() == cardsMap[x1][y].getNum()){
                            System.out.println("x1y等于xy处       "+"x ="+x + "   y="+y);
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    private void swipeDown(){
        for(int y = 0 ; y < 4; y++ ) {
            for (int x = 3; x > 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardsMap[x1][y].getNum() != 0) {
                        if (cardsMap[x][y].getNum() == 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x++;
                            break;
                        } else if (cardsMap[x][y].getNum() == cardsMap[x1][y].getNum()) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    private void swipeLeft(){
        for(int x = 0 ; x < 4; x++ ){
            for(int y = 0  ; y < 4 ; y++){
                for (int y1 = y + 1;y1 < 4 ; y1++){
                    if(cardsMap[x][y1].getNum() != 0){
                        if(cardsMap[x][y].getNum() == 0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum() );
                            cardsMap[x][y1].setNum(0);
                            y--;
                            break;
                        }
                        else if(cardsMap[x][y].getNum() == cardsMap[x][y1].getNum()){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            break;
                        }
                    }
                }
            }
        }

    }
    private void swipeRight(){

        for(int x = 0 ; x < 4; x++ ){
            for(int y = 3  ; y >= 0 ; y--){
                for (int y1 = y - 1;y1 >= 0 ; y1--){
                    if(cardsMap[x][y1].getNum() != 0){
                        if(cardsMap[x][y].getNum() == 0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum() );
                            cardsMap[x][y1].setNum(0);
                            y++;
                            break;
                        }
                        else if(cardsMap[x][y].getNum() == cardsMap[x][y1].getNum()){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            break;
                        }
                    }
                }
            }
        }

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.min(w,h) - 10)/4;
        addCards(cardWidth,cardWidth);
        startGame();
    }
    private void startGame(){
        for(int y = 0;y < 4; y++){
            for(int x = 0; x < 4; x++){
                cardsMap[x][y].setNum(0);
            }
        }
        addRandomNum();addRandomNum();
    }
    private void addCards(int cardWidth,int cardHeight){
        Card c;
        for(int i = 0; i < 4 ; i++){
            for(int j = 0;j < 4; j++){
                c= new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);

                cardsMap[i][j] = c;
            }
        }
    }

    private Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();
    private void addRandomNum(){
        emptyPoints.clear();
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++)
            {
                if(cardsMap[x][y].getNum() <= 0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        //随机移除一个点 ，设置非0，math.random生成的是0到1之间的随机数
        Point p = emptyPoints.remove((int)(Math.random() * emptyPoints.size()));
        cardsMap[p.x][p.y].setNum((Math.random()>0.1? 2:4));
    }

}
