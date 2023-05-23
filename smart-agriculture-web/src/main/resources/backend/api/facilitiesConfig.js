function getFacilitiesConfigPage(params){
    return $axios({
        url: '/facilities/config/page',
        method: 'get',
        params
    })
}

function deleteFacilitiesConfig(id) {
    return $axios({
        url: '/facilities/config',
        method: 'delete',
        params: { id }
    })
}

function editFacilitiesConfig(params) {
    return $axios({
        url: '/facilities/config',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/facilities/config`,
        method: 'get',
        params: { id }
    })
}

function addFacilitiesConfig(params) {
    return $axios({
        url: '/facilities/config',
        method: 'post',
        data: { ...params }
    })
}

