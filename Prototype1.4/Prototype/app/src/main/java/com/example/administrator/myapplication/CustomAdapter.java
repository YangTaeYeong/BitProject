package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2015-08-28.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<Item> {
    // 레이아웃 XML을 읽어들이기 위한 객체
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, ArrayList<Item> object) {
        // 상위 클래스의 초기화 과정
        // context, 0, 자료구조
        super(context, 0, object);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    // 자신이 만든 xml의 스타일로 보이기 위한 구문
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = null;
        final int pos = position;
        final Context context = parent.getContext();

        // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
        if (v == null) {
            // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
            view = mInflater.inflate(R.layout.custom_item, null);
        } else {
            view = v;
        }

        // 자료를 받는다.
        final Item data = this.getItem(position);
        if (data != null) {
            // 화면 출력

            TextView tv_title = (TextView) view.findViewById(R.id.txt_title);
            TextView tv_artist = (TextView) view.findViewById(R.id.txt_artist);
            TextView tv_genre = (TextView) view.findViewById(R.id.txt_genre);

            // 텍스트뷰1에 getLabel()을 출력 즉 첫번째 인수값

            tv_title.setText(data.getTitle());
            tv_artist.setText(data.getArtist());
            tv_genre.setText(data.getGenre());

            ImageView imageview = (ImageView) view.findViewById(R.id.img_album);

            if(data.getImageUrl() != null) UrlImageViewHelper.setUrlDrawable(imageview, data.getImageUrl());

            // 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값
            //imageview.setImageResource(data.getImageUrl());

//			view.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // 터치 시 해당 아이템 이름 출력
//                    //Toast.makeText(context, "리스트 클릭 : "+data.getItemName(), Toast.LENGTH_SHORT).show();
////                	Intent in = new Intent(this,DetailInfoActivity.class);
////                	startActivity(in);
//                }
//
//            });
//

        }
        return view;
    }

}

/*
 * public class CustomAdapter extends ArrayAdapter<CData> {
 *
 * //문자열을 보관 할 ArrayList private ArrayList<String> m_List;
 *
 * //생성자
 *
 * public CustomAdapter{ m_List = new ArrayList<String>(); }
 *
 * //현재 아이템의 수를 리턴
 *
 * @Override public int getCount() { // TODO Auto-generated method stub return
 * m_List.size(); }
 *
 * //현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
 *
 * @Override public Object getItem(int position) { // TODO Auto-generated method
 * stub return m_List.get(position); }
 *
 * //아이템 position의 ID값 리턴
 *
 * @Override public long getItemId(int position) { // TODO Auto-generated method
 * stub return position; }
 *
 * // 출력 될 아이템 관리
 *
 * @Override public View getView(int position, View convertView, ViewGroup
 * parent) { final int pos = position; final Context context =
 * parent.getContext(); CustomHolder holder; TextView text1; TextView text2;
 * TextView text3; TextView text4; TextView text5; TextView text6;
 *
 * // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴 if ( convertView
 * == null ) { // view가 null일 경우 커스텀 레이아웃을 얻어 옴 LayoutInflater inflater =
 * (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 * convertView = inflater.inflate(R.layout.custom_item, parent, false);
 *
 * text1 = (TextView) convertView.findViewById(R.id.TextView1); text2 =
 * (TextView) convertView.findViewById(R.id.TextView2); text3 = (TextView)
 * convertView.findViewById(R.id.TextView3); text4 = (TextView)
 * convertView.findViewById(R.id.TextView4); text5 = (TextView)
 * convertView.findViewById(R.id.TextView5); text6 = (TextView)
 * convertView.findViewById(R.id.TextView6);
 *
 *
 * // 홀더 생성 및 Tag로 등록 holder = new CustomHolder(); holder.m_TextView = text1;
 * holder.m_TextView = text2; holder.m_TextView = text3; holder.m_TextView =
 * text4; holder.m_TextView = text5; holder.m_TextView = text6;
 *
 *
 * convertView.setTag(holder); } else { holder = (CustomHolder)
 * convertView.getTag(); text1 = holder.m_TextView;
 *
 * }
 *
 * // Text 등록 //text1.setText(m_List.get(position));
 *
 * // 버튼 이벤트 등록 text1.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) { // 터치 시 해당 아이템 이름 출력
 * Toast.makeText(context, m_List.get(pos), Toast.LENGTH_SHORT).show(); } });
 *
 * // 리스트 아이템을 터치 했을 때 이벤트 발생 convertView.setOnClickListener(new
 * OnClickListener() {
 *
 * @Override public void onClick(View v) { // 터치 시 해당 아이템 이름 출력
 * Toast.makeText(context, "리스트 클릭 : "+m_List.get(pos),
 * Toast.LENGTH_SHORT).show(); } });
 *
 * // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생 convertView.setOnLongClickListener(new
 * OnLongClickListener() {
 *
 * @Override public boolean onLongClick(View v) { // 터치 시 해당 아이템 이름 출력
 * Toast.makeText(context, "리스트 롱 클릭 : "+m_List.get(pos),
 * Toast.LENGTH_SHORT).show(); return true; } });
 *
 * return convertView; }
 *
 * private class CustomHolder { TextView m_TextView; Button m_Btn; }
 *
 * public void add(String _msg) { m_List.add(_msg); } }
 */

