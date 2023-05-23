function getCropPage(params){
    return $axios({
        url: '/crop/crop/page',
        method: 'get',
        params
    })
}

function deleteCrop(id) {
    return $axios({
        url: '/crop/crop',
        method: 'delete',
        params: { id }
    })
}

function editCrop(params) {
    return $axios({
        url: '/crop/crop',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/crop/crop`,
        method: 'get',
        params: { id }
    })
}

function addCrop(params) {
    return $axios({
        url: '/crop/crop',
        method: 'post',
        data: { ...params }
    })
}

