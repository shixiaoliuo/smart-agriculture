function login(params){
    return $axios({
        url: '/sys/user/login',
        method: 'post',
        data: { ...params }
    })
}

function logout(params){
    return $axios({
        url: '/sys/user/logout',
        method: 'post',
        data: { ...params }
    })
}

function findMenu() {
    return $axios({
        url: '/sys/module/menu',
        method: 'get'
    })
}
