package com.example.pirmaslab.mybatis.dao;

import com.example.pirmaslab.mybatis.model.Player;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface PlayerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Mon Apr 29 12:33:09 EEST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Mon Apr 29 12:33:09 EEST 2024
     */
    int insert(Player record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Mon Apr 29 12:33:09 EEST 2024
     */
    Player selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Mon Apr 29 12:33:09 EEST 2024
     */
    List<Player> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Mon Apr 29 12:33:09 EEST 2024
     */
    int updateByPrimaryKey(Player record);
}