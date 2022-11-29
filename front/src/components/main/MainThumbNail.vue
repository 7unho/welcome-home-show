<template>
    <div>
        <b-tabs content-class="mt-3">
            <notice-thumb-nail-item></notice-thumb-nail-item>
            <news-thumb-nail-item></news-thumb-nail-item>
        </b-tabs>
    </div>
</template>

<script>
import NoticeThumbNailItem from '@/components/notice/item/NoticeThumbNailItem';
import NewsThumbNailItem from '@/components/news/item/NewsThumbNailItem';


export default {
    name: "MainThumbNail",
    components: {
        NoticeThumbNailItem,
        NewsThumbNailItem
    }
}
</script>

<style>

</style>