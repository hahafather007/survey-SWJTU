package com.swjtu.survey.view.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.android.framekit.utils.DeviceUtil;
import com.doodle.DowerLayout;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.layers.RasterLayer;
import com.esri.arcgisruntime.loadable.LoadStatusChangedEvent;
import com.esri.arcgisruntime.loadable.LoadStatusChangedListener;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.raster.Raster;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.floatview.FloatWindowManager;
import com.floatview.base.BaseActivity;
import com.swjtu.survey.R;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TiffGIsActivity extends BaseActivity {
    private MapView mMapView;
    Graphic graphic;
    Point ptMarker = new Point(403254.6651694332, 3404662.8115282874);
    private Callout callout;
    private DowerLayout mDoodleLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tiff_gis;
    }

    @Override
    protected void initData() {
        floatWindowType = FloatWindowManager.FW_TYPE_ROOT_VIEW;
    }

    @Override
    protected void initView() {
//        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud8903090910,none,S080TK8EL729L50JT009");

        mDoodleLayout = findViewById(R.id.dl_tiff);
        mMapView = findViewById(R.id.arc_map);
        mMapView.setAttributionTextVisible(false);
        String imgPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tiff/xipu_mosaic_group1.tif";
        File ﬁle = new File(imgPath);
        if (ﬁle.exists()) {
            Log.i("tang", "path:" + ﬁle.getAbsolutePath());
        } else {
            Log.i("tang", "path: not exits:" + imgPath);
        }
        Raster raster = new Raster(ﬁle.getAbsolutePath());
        raster.addLoadStatusChangedListener(new LoadStatusChangedListener() {
            @Override
            public void loadStatusChanged(LoadStatusChangedEvent loadStatusChangedEvent) {
                Log.i("tang", "--" + loadStatusChangedEvent.getNewLoadStatus());
            }
        });
        raster.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Log.i("tang", "--done listener");
            }
        });
        RasterLayer rasterLayer = new RasterLayer(raster);
        ArcGISMap map = new ArcGISMap(); //        Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR; //        double latitude = 34.0270; //        double longitude = -118.8050; //        int levelOfDetail = 13; //        ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
        map.getOperationalLayers().add(rasterLayer);
        map.addLoadStatusChangedListener(new LoadStatusChangedListener() {
            @Override
            public void loadStatusChanged(LoadStatusChangedEvent loadStatusChangedEvent) {
                Log.i("tang", "11done listener");
            }
        });
        map.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Log.i("tang", "22done listener");
            }
        });
        map.addBasemapChangedListener(new ArcGISMap.BasemapChangedListener() {
            @Override
            public void basemapChanged(ArcGISMap.BasemapChangedEvent basemapChangedEvent) {
                Log.i("tang", "33done listener");
            }
        });
        mMapView.setMap(map);
        PictureMarkerSymbol pic;
        GraphicsOverlay overlay = new GraphicsOverlay();
        try {
            pic = PictureMarkerSymbol.createAsync((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.ic_marker)).get();
            pic.setHeight(24);
            pic.setWidth(24);
            pic.addDoneLoadingListener(new Runnable() {
                @Override
                public void run() {
                    //添加⼀个overlay
                    Log.i("tang", "添加⼀个marker");
                    mMapView.getGraphicsOverlays().add(overlay);

                    graphic = new Graphic(ptMarker, pic);
                    overlay.getGraphics().add(graphic);
                }

            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
                Point point = mMapView.screenToLocation(screenPoint);
                mMapView.setViewpointCenterAsync(point).addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("tang", "平移到这⾥--Point:" + point.getX() + "--" + point.getY());
                        if (overlay == null) {
                            return;
                        }

                        ListenableFuture<List<IdentifyGraphicsOverlayResult>> listListenableFuture = mMapView.identifyGraphicsOverlaysAsync(screenPoint, 20, false, 5);
                        listListenableFuture.addDoneListener(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    List<IdentifyGraphicsOverlayResult> result = listListenableFuture.get();
                                    for (int i = 0; i < result.size(); i++) {
                                        for (int i1 = 0; i1 < result.get(i).getGraphics().size(); i1++) {
                                            Point p = (Point) result.get(i).getGraphics().get(i1).getGeometry();
                                            Log.i("tang", "点击点坐标" + p.getX() + "---" + p.getY());
                                            callout = mMapView.getCallout();
                                            callout.setShowOptions(new Callout.ShowOptions());
                                            Callout.Style calloutStyle = new Callout.Style(TiffGIsActivity.this);
                                            calloutStyle.setCornerRadius(6);
                                            calloutStyle.setLeaderPosition(Callout.Style.LeaderPosition.LOWER_MIDDLE);
                                            calloutStyle.setBorderColor(ContextCompat.getColor(TiffGIsActivity.this, R.color.gray));
                                            calloutStyle.setBorderWidth(1);
                                            calloutStyle.setMaxHeight(240);

                                            View viewMarker = LayoutInflater.from(TiffGIsActivity.this).inflate(R.layout.view_callout, null, false);
                                            View ivMarkerClose = viewMarker.findViewById(R.id.iv_marker_close);
                                            ivMarkerClose.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    disMissCallOut();
                                                }
                                            });
                                            Button btn_folat = viewMarker.findViewById(R.id.btn_folat);
                                            btn_folat.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Log.i("tang", "显示弹窗");
                                                    floatWindowType = FloatWindowManager.FW_TYPE_ROOT_VIEW;
                                                    showFloatWindowDelay();
                                                }
                                            });
                                            callout.show(viewMarker, point);
                                            callout.setStyle(calloutStyle);
                                            Log.i("tang", "要素：" + result.get(i).getGraphics().get(i1).getAttributes());
                                        }
                                    }
                                } catch (ExecutionException ex) {
                                    ex.printStackTrace();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
//                        int[] graphicIDs = overlay.getGraphics().getGraphicIDs(x, y, 25);
//                        if (graphicIDs != null && graphicIDs.length > 0) {
//                            LayoutInﬂater inﬂater = LayoutInﬂater
//                                    .from(HelloWorldActivity.this);
//                            View view = inﬂater.inﬂate(R.layout.callout, null);
//                            Graphic gr = graphicsLayer.getGraphic(graphicIDs[0]);
//                            com.esri.core.geometry.Point location = new com.esri.core.geometry.Point(
//                                    1.2905771616285184E7, 3035967.556712447);
//                            Callout callout = mMapView.getCallout();
//                            callout.setStyle(R.xml.calloutstyle);
//                            callout.setOffset(0, -15);
//                            callout.show(location, view);
//                        }
                    }
                });
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }
        });
    }

    public void openCad(){

    }

    @Override
    protected void onFloatWindowClick() {
        super.onFloatWindowClick();
        //显示画板
        mDoodleLayout.setVisibility(View.VISIBLE);
        //获取到fw内容的x，y，w，h
        float scaleX = floatWindowManager.getFloatView().getParams().contentWidth / DeviceUtil.INSTANCE.getScreenWidth(this);
        float scaleY = floatWindowManager.getFloatView().getParams().contentHeight / DeviceUtil.INSTANCE.getScreenHeight(this);
        Log.i("tang","scale:"+scaleX+"--"+scaleY);
//        AnimationUtil.startFillInAnimator(mDoodleLayout, scaleX,scaleY,floatWindowManager.getFloatView().getParams().x+(floatWindowManager.getFloatView().getParams().contentWidth/2.0f), floatWindowManager.getFloatView().getParams().y+(floatWindowManager.getFloatView().getParams().contentHeight/2.0f));
    }

    /**
     * 测试刺点：Point:403286.3223070835--3404676.889257169
     */
    private void showMarker() {
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            mMapView.dispose();
        }
        super.onDestroy();
    }

    private boolean isPointLimited(Point src, Point dest) {
        Log.i("tang", "x:Math.abs(src.getX() - dest.getX()):" + Math.abs(src.getX() - dest.getX()) + "--y:Math.abs(src.getY() - dest.getY())" + Math.abs(src.getY() - dest.getY()));
        return (Math.abs(src.getX() - dest.getX()) < 10) && (Math.abs(src.getY() - dest.getY()) < 10);
    }

    private void disMissCallOut() {
        if (callout != null) {
            callout.dismiss();
        }
    }
}