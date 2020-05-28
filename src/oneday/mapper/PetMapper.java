package oneday.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import oneday.pojo.Pet;

public interface PetMapper {
	@Select("select * from pet where phone=#{0}")
	Pet selByPhonePet(Long phone);

	@Update("update pet set petname=#{0} where phone=#{1}")
	int updPetName(String petName,Long phone);

	@Update("update pet set weight=#{0} where phone=#{1}")
	int updPetWeight(int weight,Long phone);
	
	@Update("update pet set intimacy=#{0} where phone=#{1}")
	int updPetIntimacy(int intimacy,Long phone);

	@Insert("insert into pet values(default,#{phone},#{petname},#{birthday},#{weight},#{intimacy})")
	int insPet(Pet pet);
}