function getUserPage(params){
    return $axios({
        url: '/sys/user/page',
        method: 'get',
        params
    })
}

function deleteUser(id) {
    return $axios({
        url: '/sys/user',
        method: 'delete',
        params: { id }
    })
}

function editUser(params) {
    return $axios({
        url: '/sys/user',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/sys/user`,
        method: 'get',
        params: { id }
    })
}

function addUser(params) {
    return $axios({
        url: '/sys/user',
        method: 'post',
        data: { ...params }
    })
}

function checkUsername(params) {
    return $axios({
        url: `/sys/user/checkUsername`,
        method: 'post',
        data:{...params}
    })
}





function findRoleList() {
    return $axios({
        url: `/sys/role/list`,
        method: 'get',
    })
}

function findUserRoleList(id) {
    let params = {'userId':id};
    return $axios({
        url: `/sys/role/userRole`,
        method: 'get',
        params
    })
}

function updateUserRole(params) {
    return $axios({
        url: `/sys/role/userRole`,
        method: 'post',
        data:{...params}
    })
}