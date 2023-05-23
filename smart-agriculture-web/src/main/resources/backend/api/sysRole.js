function getRolePage(params){
    return $axios({
        url: '/sys/role/page',
        method: 'get',
        params
    })
}

function deleteRole(id) {
    return $axios({
        url: '/sys/role',
        method: 'delete',
        params: { id }
    })
}

function editRole(params) {
    return $axios({
        url: '/sys/role',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/sys/role`,
        method: 'get',
        params: { id }
    })
}

function addRole(params) {
    return $axios({
        url: '/sys/role',
        method: 'post',
        data: { ...params }
    })
}









function findModuleList() {
    return $axios({
        url: '/sys/module/list',
        method: 'get',
    })
}

function findRoleModuleList(id) {
    let params = {'roleId':id};
    return $axios({
        url: `/sys/module/roleModule`,
        method: 'get',
        params
    })
}


function updateRoleModule(params) {
    return $axios({
        url: `/sys/module/roleModule`,
        method: 'post',
        data:{...params}
    })
}
