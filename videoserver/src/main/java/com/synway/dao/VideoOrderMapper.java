package com.synway.dao;

import com.synway.domain.VideoOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单dao
 */
public interface VideoOrderMapper {

    @Insert("insert into video_order(openid,out_trado_no,state,create_time,notify_time,total_fee," +
            "nickname,head_img,video_id,video_title,video_img,user_id,ip,del)" +
            "values" +
            "(#{openid},#{outReadoNo},#{state},#{createTime},#{notifyTime}," +
            "#{totalFee},#{nickname},#{headImg},#{videoId}},#{videoTitle}," +
            "#{videoImg},#{userId},#{ip},#{del})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int insert(VideoOrder videoOrder);

    @Select("select * from video_order where id=#{order_id} and del=0")
    VideoOrder findbyId(@Param("order") int id);

    @Update("update video_order set del=0 id = #{id} and user_id=#{userId}")
    int delete(@Param("id") int id,@Param("userId") int userId);

    /**
     * 根据交易订单号获取订单对象
     * @param outTradeNo
     * @return
     */
    @Select("select * from video_order where oout_trade_no=#{outTradeNo} and del=0")
    VideoOrder findByOutTradeNo(@Param("oout_trade_no") String outTradeNo);


    @Select("select * from video_order where user_id=#{userId}")
    List<VideoOrder> findMyOrderList(int userId);

    /**
     * 根据订单流水号更新
     */
    @Update("update video_order set state=#{state},notify_time=#{notifyTime},openid=#{openid}" +
            "where out_trade_no=#{outTradeNo} and state=0 and del=0")
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);
}
