package com.example.wuzhe.game_2048;
import com.example.wuzhe.game_2048.MainActivity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

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
                            }
                            else if (offsetX > 5) {
                                System.out.println("right");
                            }
                        }
                        else
                        {
                            if (offsetY < -5) {
                                //Context context ;
                                //Toast.makeText(context,"left", Toast.LENGTH_SHORT).show();
                                System.out.println("UP");
                            }
                            else if (offsetY > 5) {
                                System.out.println("Down");
                            }
                        }

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }



}
