package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.SysModule;
import com.lxl.agro.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @company：千峰教育
 * @author：zhy
 * @date：2023-02-17
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id，查询用户具备的角色
     * @param userId
     * @return
     */
    List<Long> selectByUserId(Long userId);

    /**
     * 根据用户id删除用户角色关联表中数据
     * @param userId
     */
    int deleteByUserId(Long userId);

    /**
     * 批量插入用户角色关联信息
     * @param userId
     * @param roleIds
     */
    int insertUserRole(Long userId,List roleIds);

    /**
     * 根据roleid，查询用户具备的权限
     * @param roleId
     * @return
     */
    List<SysModule> selectByRoleId(Long roleId);

    int insertRoleModule(Long roleId, List<Long> moduleIds);

    void deleteByRoleId(Long roleId);

    List<SysModule> selectByParentId(Long parentId);
}
