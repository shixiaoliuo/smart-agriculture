function getWarningPage(params){
    return $axios({
        url: '/warning/warning/page',
        method: 'get',
        params
    })
}

function deleteStatisticsTHTB(id) {
    return $axios({
        url: '/statistics/THTBLogs',
        method: 'delete',
        params: { id }
    })
}

function editStatisticsTHTB(params) {
    return $axios({
        url: '/statistics/THTBLogs',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/statistics/THTBLogs`,
        method: 'get',
        params: { id }
    })
}

function addStatisticsTHTB(params) {
    return $axios({
        url: '/statistics/THTBLogs',
        method: 'post',
        data: { ...params }
    })
}