function getLogPage(params){
    return $axios({
        url: '/sys/log/page',
        method: 'get',
        params
    })
}

function deleteLog(id) {
    return $axios({
        url: '/sys/log',
        method: 'delete',
        params: { id }
    })
}

function editLog(params) {
    return $axios({
        url: '/sys/log',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/sys/log`,
        method: 'get',
        params: { id }
    })
}

function addLog(params) {
    return $axios({
        url: '/sys/log',
        method: 'post',
        data: { ...params }
    })
}