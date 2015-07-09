/**
 * Title: Palette
 * Tag: Palette, Swatch, Color
 * Update: 2015/07/09
 * Description: 介绍如何从Bitmap中提取颜色
 * 
 *	Palette.Builder
 *	同步创建Palette对象，推荐在后台线程中使用
 *      generate()
 *	异步
 *	generate(Palette.PaletteAsyncListener listener)
 *
 *	创建多个Swatch对象，默认是16个，越多耗时越久
 *	maximumColorCount(int numOfSwatches)
 *
 *	重新设定bitmap的大小，越大耗时越久，越小则颜色精确度越低
 *	resizeBitmapSize(int maxDimension)
 *   
 *	Swatch 对象包含内容
 *   	1.颜色的RGB值和HSL值
 *   	2.像素数量
 *    	3.提供给Title文字的主色调
 *    	4.提供给Content文字的主色调
 *
 *      每一个Palette包含预先定义好的6个色调
 *    	1.vibrant  (使用最多)
 *    	2.light vibrant
 *    	3.dark vibrant (使用最多)
 *    	4.muted
 *    	5.lignt muted
 *    	6.dark muted
 *      注意，上述的色调可以是null
 */

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView tv1 = (TextView) findViewById(R.id.tv1);
    final TextView tv2 = (TextView) findViewById(R.id.tv2);
    final TextView tv3 = (TextView) findViewById(R.id.tv3);
    final TextView tv4 = (TextView) findViewById(R.id.tv4);


    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test_img);
    Palette.from(bitmap) //直接从图片获取Palette.Builder 对象
	.generate(new Palette.PaletteAsyncListener() {
	    @Override
	    public void onGenerated(Palette palette) {
		palette.getSwatches();// 获取所有的swatch对象，无序

		setViewSwatch(tv1, palette.getVibrantSwatch()); //将颜色应用到View上
		setViewSwatch(tv2, palette.getLightVibrantSwatch());
		setViewSwatch(tv3, palette.getMutedSwatch());
		setViewSwatch(tv4, palette.getDarkVibrantSwatch());
	    }
	});

}

public void setViewSwatch(TextView view, Palette.Swatch swatch){
    if(swatch != null){
	view.setTextColor(swatch.getTitleTextColor());
	view.setBackgroundColor(swatch.getRgb());
	view.setVisibility(View.VISIBLE);
    }else{
	view.setVisibility(View.GONE);
    }
}
