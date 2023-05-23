function getModulePage(params){
    return $axios({
        url: '/sys/module/page',
        method: 'get',
        params
    })
}






















function deleteModule(id) {
    return $axios({
        url: '/sys/module',
        method: 'delete',
        params: { id }
    })
}

function editModule(params) {
    return $axios({
        url: '/sys/module',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/sys/module`,
        method: 'get',
        params: { id }
    })
}

function addModule(params) {
    return $axios({
        url: '/sys/module',
        method: 'post',
        data: { ...params }
    })
}