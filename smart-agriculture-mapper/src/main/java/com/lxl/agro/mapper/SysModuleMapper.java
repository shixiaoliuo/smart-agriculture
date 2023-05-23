package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.SysModule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @company：千峰教育
 * @author：zhy
 * @date：2023-02-17
 */
@Mapper
public interface SysModuleMapper extends BaseMapper<SysModule> {

    /**
     * 根据用户id查询用户具备的菜单
     * @param userId
     * @return
     */
    List<SysModule> selectByUserId(Long userId);

    /**
     * 根据角色id查询角色具备的菜单
     * @param roleId
     * @return
     */
    List<Long> selectByRoleId(Long roleId);

    /**
     * 根据角色id删除当前角色的权限
     * @param roleId
     */
    void deleteByRoleId(Long roleId);

    /**
     * 建立当前角色和权限的关联
     * @param roleId
     * @param moduleIds
     */
    void insertRoleModule(Long roleId,List moduleIds);

    List<SysModule> selectParentId();
}
