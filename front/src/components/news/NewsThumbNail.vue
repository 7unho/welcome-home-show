<template>
    <b-col>
        <b-list-group class="mr-4 mt-4">
            <b-list-group-item href="#" active class="flex-column align-items-start">
                <div class="d-flex w-80 justify-content-between">1</div>
            </b-list-group-item>

            <b-list-group-item href="#" class="flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">2</div>
            </b-list-group-item>

            <b-list-group-item href="#" disabled class="flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">3</div>
            </b-list-group-item>
        </b-list-group>
    </b-col>
</template>

<script>
export default {
    name: "NewsThumbNail"
}
</script>

<style>

</style>