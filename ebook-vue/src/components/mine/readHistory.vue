<template>
  <div class="read_history">
    <div class="read_history_catalog">
      <header class="component_title">
        阅读记录
      </header>

      <ul class="read_history_list">
        <li
          class="read_history_item"
          v-for="(item, key) in readHistoryList"
          :key="key"
          @click="toRead(item.chapter)"
        >
          <div class="read_history title">
            {{ item.chapter.title }}
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import { getReadHistory } from "@/api/api";
import { ebookMixin } from "@/util/mixin";

export default {
  mixins: [ebookMixin],

  data() {
    return {
      readHistoryList: [],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      getReadHistory().then((Response) => {
        if (Response.data.code == 200) {
          let array = Response.data.result;
          for (let index = 0; index < array.length; index++) {
            array[index].chapter = JSON.parse(array[index].chapter);
          }
          this.readHistoryList = array;
        }
      });
    },
    toRead(chapter) {
      this.parsingBook(chapter);
      this.$router.push("/book-reader");
    },
  },
};
</script>

<style scoped>
.read_history_catalog {
  position: relative;
  margin: 20px 16px;
}

.component_title {
  font-size: 22px;
  margin: 12px 0;
  text-align: center;
}
.read_history {
  font-size: 16px;
}

.read_history_item:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}

ul {
  overflow: auto;
  width: 100%;
  height: 100%;
}

li {
  -webkit-box-direction: normal;
  list-style: none;
  height: 52px;
  line-height: 52px;
  border-radius: 0;
  border: solid #ebedf1;
  overflow: hidden;
  border-width: 0 0 1px;
  padding: 0 0px 0 28px;
  display: flex;
}
</style>
