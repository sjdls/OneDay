package oneday.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import oneday.pojo.Diary;
import oneday.pojo.Page;
import oneday.pojo.SearchItems;
import oneday.pojo.User;

public interface DiaryMapper {
	/**
	 * 通过日期查询日记
	 * @param date
	 * @param phone
	 * @return
	 */
	@Select("select * from diary where date=#{0} && phone=#{1} && draft = false")
	List<Diary> selByDateDiaries(Date date, Long phone);

	/**
	 * 返回写过日记的日期
	 * @param phone
	 * @return
	 */
	@Select("select * from diary where phone=#{0} && draft = false")
	List<Diary> selSortByDateDiaries(Long phone);

	int updDiary(Diary diary);
	
	@Insert("insert into diary values(default,#{phone},#{title},#{date},#{weather},#{mood},#{event},#{picture},#{content},#{draft})")
	int insDiary(Diary diary);
	
	@Delete("delete from diary where id=#{0}")
	int delDiary(int id);
	
	List<Diary> selDiary(SearchItems searchItems);
	
	@Select("select * from diary where phone=#{phone} && draft = false order by date desc,id desc limit #{pageNo},#{pageSize}")
	List<Diary> selDiaryByPageDesc(Page page);
	
	@Select("select * from diary where phone=#{phone} && draft = false order by date asc,id asc limit #{pageNo},#{pageSize}")
	List<Diary> selDiaryByPageAsc(Page page);
}
